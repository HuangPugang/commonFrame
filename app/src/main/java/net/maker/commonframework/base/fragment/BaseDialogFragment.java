package net.maker.commonframework.base.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alipay.euler.andfix.patch.PatchManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;

import net.maker.commonframework.base.activity.BaseActivity;
import net.maker.commonframework.base.mvp.presenter.BasePresetner;
import net.maker.commonframework.base.mvp.view.IBaseFragmentView;
import net.maker.commonframework.base.mvp.view.MvpView;
import net.maker.commonframework.base.rx.support.RxDialogFragment;
import net.maker.commonframework.common.GlobUtils;
import net.maker.commonframework.common.StatusBarUtil;
import net.maker.commonframework.network.BaseHttp;
import net.maker.commonframework.network.LogInterceptor;
import net.maker.commonframework.network.RunApiService;

import java.lang.reflect.Field;

import javax.inject.Inject;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by MakerYan on 16/4/1 17:56.
 * Email: yanl@59store.com
 */
public abstract class BaseDialogFragment extends RxDialogFragment implements IBaseFragmentView {

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

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((BaseActivity) activity).getBaseComponent().inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isBindEventBusHere()) {
            EventBus.getDefault().register(this);
        }
        Bundle extras = getArguments();
        if (null != extras) {
            getExtras(extras);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getLayoutResID() != 0) {
            View inflate = inflater.inflate(getLayoutResID(), container, false);
            ButterKnife.bind(this, inflate);
            initializerComponent();
            assignViews(inflate);
            registListener();
            return inflate;
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        doAction();
        KLog.a(getSharedPreferences());
        KLog.a(mResources);
        KLog.a(mRunApiService);
        KLog.a(mBaseHttp);
        KLog.a(mLogInterceptor);
        KLog.a(mMvpView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isBindEventBusHere()) {
            EventBus.getDefault().unregister(this);
        }
        mGlobUtils.setViewBgAsNull((ViewGroup) getView());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        getPresenter().detachView(true);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // for bug ---> java.lang.IllegalStateException: Activity has been destroyed
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
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
     * @param <P>
     * @return 获取当前Presenter
     */
    protected abstract <P extends BasePresetner> P getPresenter();

    /**
     * @return 是否绑定EventBus?
     */
    protected abstract boolean isBindEventBusHere();

    /**
     * 获取控件
     */
    protected abstract void assignViews(View view);

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

    @Override
    public MvpView getMvpView() {
        return mMvpView;
    }


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
        return mActivity.getSharedPreferences("59store", Context.MODE_PRIVATE);
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
     * @param clazz 启动另一个activity并finish
     */
    @Override
    public void startToActivity(Class<?> clazz, Bundle extras) {

    }

    /**
     * @param clazz 启动另一个activity并finish
     */
    @Override
    public void startToActivityAndFinish(Class<?> clazz, Bundle extras) {

    }

    /**
     * @param layoutResId 容器ID
     * @param fragment    需要添加的Fragment
     */
    @Override
    public void addFragment(int layoutResId, BaseFragment fragment, boolean isAddToBackStack) {

    }

    /**
     * @param layoutResId 容器ID
     * @param fragment    需要替换的Fragment
     */
    @Override
    public void replaceFragment(int layoutResId, BaseFragment fragment, boolean isAddToBackStack) {

    }

    /**
     * @return 获取FragmentManager
     */
    @Override
    public FragmentManager getFM() {
        return getFragmentManager();
    }
}
