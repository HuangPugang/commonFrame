package net.maker.commonframework.base.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.alipay.euler.andfix.patch.PatchManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import net.maker.commonframework.base.dagger.component.BaseActivityComponent;
import net.maker.commonframework.base.fragment.BaseFragment;
import net.maker.commonframework.base.mvp.presenter.BasePresetner;
import net.maker.commonframework.base.mvp.view.IBaseActivityView;
import net.maker.commonframework.base.mvp.view.MvpView;
import net.maker.commonframework.base.rx.support.RxAppCompatActivity;
import net.maker.commonframework.common.GlobUtils;
import net.maker.commonframework.common.StatusBarUtil;
import net.maker.commonframework.network.BaseHttp;
import net.maker.commonframework.network.LogInterceptor;
import net.maker.commonframework.network.RunApiService;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by MakerYan on 16/3/31 16:55.
 * Email: yanl@59store.com
 */
public abstract class BaseActivity extends RxAppCompatActivity implements IBaseActivityView {

    @Inject
    protected BaseActivity mBaseActivity;
    @Inject
    protected Activity mActivity;
    @Inject
    protected Resources mResources;
    @Inject
    protected RunApiService mRunApiService;
    @Inject
    protected BaseHttp mBaseHttp;
    @Inject
    protected LogInterceptor mLogInterceptor;
    @Inject
    protected MvpView mMvpView;
    @Inject
    protected GlobUtils mGlobUtils;
    @Inject
    protected StatusBarUtil mStatusBarUtil;
    @Inject
    protected Picasso mPicasso;

    protected BaseActivityComponent mComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseComponent();
        initializerComponent();
        beforeSetContentView();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            getExtras(extras);
        }
        if (isBindEventBusHere()) {
            EventBus.getDefault().register(this);
        }
        setContentView(getLayoutResID());
        assignViews();
        initWidget(savedInstanceState);
        registListener();
        doAction();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (isBindEventBusHere()) {

        }
        getPresenter().detachView(true);
    }

    @Override
    public BaseActivity getBaseActivity() {
        return BaseActivity.this;
    }

    @Override
    public Activity getCurrentActivity() {
        return this;
    }

    @Override
    public Picasso getPicasso() {
        return mPicasso;
    }

    @Override
    public SharedPreferences getSharedPreferences() {
        return getSharedPreferences("59store", Context.MODE_PRIVATE);
    }

    @Override
    public RunApiService getRunApiService() {
        return mRunApiService;
    }

    @Override
    public BaseHttp getBaseHttp() {
        return mBaseHttp;
    }

    @Override
    public GlobUtils getGlobUtils() {
        return mGlobUtils;
    }

    /**
     * @param clazz 启动另一个activity并finish
     */
    @Override
    public void startToActivity(Class<?> clazz, Bundle extras) {
        Intent intent = new Intent(this, clazz);
        if (null != extras) {
            intent.putExtras(extras);
        }
        this.startActivity(intent);
    }

    /**
     * @param clazz 启动另一个activity并finish
     */
    @Override
    public void startToActivityAndFinish(Class<?> clazz, Bundle extras) {
        startToActivity(clazz, extras);
        finish();
    }

    /**
     * @param layoutResId 容器ID
     * @param fragment    需要添加的Fragment
     */
    @Override
    public void addFragment(int layoutResId, BaseFragment fragment, boolean isAddToBackStack) {
        FragmentManager fm = getFM();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction, fm.getFragments());
        transaction.add(layoutResId, fragment, fragment.getClass().getSimpleName());
        if (isAddToBackStack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        transaction.commitAllowingStateLoss();
    }

    /**
     * @param layoutResId 容器ID
     * @param fragment    需要替换的Fragment
     */
    @Override
    public void replaceFragment(int layoutResId, BaseFragment fragment, boolean isAddToBackStack) {
        FragmentManager fm = getFM();
        FragmentTransaction transaction = fm.beginTransaction();
        Fragment fragmentById = fm.findFragmentById(layoutResId);
        if (null == fragmentById) {
            addFragment(layoutResId, fragment, isAddToBackStack);
        } else {
            hideFragment(transaction, fm.getFragments());
            showFragment(transaction, fragmentById);
        }
    }

    protected void hideFragment(FragmentTransaction transaction, List<Fragment> fragments) {
        for (Fragment fg : fragments) {
            transaction.hide(fg);
        }
    }

    protected void showFragment(FragmentTransaction transaction, Fragment fragment) {
        transaction.show(fragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public Gson getGson() {
        return new GsonBuilder().create();
    }

    /**
     * @return BaseActivityComponent
     */
    @Override
    public BaseActivityComponent getBaseComponent() {
        if (null == mComponent) {
            mComponent = BaseActivityComponent.Initializer.init(this, this);
            mComponent.inject(this);
        }
        return mComponent;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }


    /**
     * 加载布局之前调用
     */
    protected void beforeSetContentView() {

    }

    /**
     * @return 布局Id
     */
    protected abstract int getLayoutResID();

    /**
     * @param extras 上层传过来的数据
     */
    protected abstract void getExtras(Bundle extras);

    /**
     * 初始Dagger
     */
    protected abstract void initializerComponent();

    /**
     * @return 是否绑定EventBus?
     */
    protected abstract boolean isBindEventBusHere();

    /**
     * 获取控件
     */
    protected abstract void assignViews();

    /**
     * 控件赋值
     *
     * @param savedInstanceState
     */
    protected abstract void initWidget(Bundle savedInstanceState);

    /**
     * 注册控件监听
     */
    protected abstract void registListener();

    /**
     * @return 在哪个布局上显示loading
     */
    protected abstract View getLoadingTargetView();

    /**
     * 开始处理
     */
    protected abstract void doAction();

    /**
     * @param <P>
     * @return 获取当前Presenter
     */
    protected abstract <P extends BasePresetner> P getPresenter();

    /**
     * @param text ProgressDialog Content
     */
    @Override
    public void showProgressDialog(String text) {

    }

    /**
     * 关闭ProgressDialog
     */
    @Override
    public void dismissProgressDialog() {

    }

    /**
     * 正在加载
     */
    @Override
    public void showLoading(View view) {

    }

    /**
     * 加载错误
     */
    @Override
    public void showError(View view) {

    }

    /**
     * 加载完成
     */
    @Override
    public void loadingComplete(View view) {

    }

    /**
     * 加载完成,显示内容
     */
    @Override
    public void showContent() {

    }

    /**
     * @return 获取FragmentManager
     */
    @Override
    public FragmentManager getFM() {
        return getSupportFragmentManager();
    }
}
