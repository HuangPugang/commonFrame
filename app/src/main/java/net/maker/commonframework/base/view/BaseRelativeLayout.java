package net.maker.commonframework.base.view;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.alipay.euler.andfix.patch.PatchManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import net.maker.commonframework.base.activity.BaseActivity;
import net.maker.commonframework.base.fragment.BaseFragment;
import net.maker.commonframework.base.mvp.presenter.BasePresetner;
import net.maker.commonframework.base.mvp.view.IBaseRelativeLayoutView;
import net.maker.commonframework.base.mvp.view.MvpView;
import net.maker.commonframework.common.GlobUtils;
import net.maker.commonframework.common.StatusBarUtil;
import net.maker.commonframework.network.BaseHttp;
import net.maker.commonframework.network.LogInterceptor;
import net.maker.commonframework.network.RunApiService;

import javax.inject.Inject;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import rx.Observable;

/**
 * Created by MakerYan on 16/4/8 14:46.
 * Email: yanl@59store.com
 */
public abstract class BaseRelativeLayout extends RelativeLayout implements IBaseRelativeLayoutView {

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

    public BaseRelativeLayout(Context context) {
        this(context, null);
    }

    public BaseRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ((BaseActivity) context).getBaseComponent().inject(this);
        initializerComponent();
        if (isBindEventBusHere()) {
            EventBus.getDefault().register(this);
        }
        View view = LayoutInflater.from(context).inflate(getLayoutResID(), null);
        ButterKnife.bind(this, view);
        assignViews(view);
        initWidget();
        registListener();
        addView(view);
        doAction();
    }

    /**
     * {@inheritDoc}
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public <T> Observable.Transformer<T, T> bindToLifecycle() {
        return null;
    }

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
     *
     * @param view
     */
    @Override
    public void showLoading(View view) {

    }

    /**
     * 加载错误
     *
     * @param view
     */
    @Override
    public void showError(View view) {

    }

    /**
     * 加载完成
     *
     * @param view
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
     * @return 获取Presenter
     */

    @Override
    public BaseActivity getBaseActivity() {
        return mBaseActivity;
    }

    @Override
    public Activity getCurrentActivity() {
        return mActivity;
    }

    @Override
    public Picasso getPicasso() {
        return mPicasso;
    }

    @Override
    public Gson getGson() {
        return new GsonBuilder().create();
    }

    @Override
    public SharedPreferences getSharedPreferences() {
        return mBaseActivity.getSharedPreferences("59store", Context.MODE_PRIVATE);
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
        return mMvpView.getGlobUtils();
    }

    /**
     * @return 获取FragmentManager
     */
    @Override
    public FragmentManager getFM() {
        return mBaseActivity.getSupportFragmentManager();
    }

    /**
     * @param clazz  启动另一个activity并finish
     * @param extras
     */
    @Override
    public void startToActivity(Class<?> clazz, Bundle extras) {

    }

    /**
     * @param clazz  启动另一个activity并finish
     * @param extras
     */
    @Override
    public void startToActivityAndFinish(Class<?> clazz, Bundle extras) {

    }

    /**
     * @param layoutResId      容器ID
     * @param fragment         需要添加的Fragment
     * @param isAddToBackStack
     */
    @Override
    public void addFragment(int layoutResId, BaseFragment fragment, boolean isAddToBackStack) {

    }

    /**
     * @param layoutResId      容器ID
     * @param fragment         需要替换的Fragment
     * @param isAddToBackStack
     */
    @Override
    public void replaceFragment(int layoutResId, BaseFragment fragment, boolean isAddToBackStack) {

    }

    /**
     * @return 布局Id
     */
    protected abstract int getLayoutResID();

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
    protected abstract void assignViews(View view);

    /**
     * 控件赋值
     */
    protected abstract void initWidget();

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
}
