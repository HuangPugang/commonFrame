package net.maker.commonframework.base.mvp.view;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.alipay.euler.andfix.patch.PatchManager;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import net.maker.commonframework.base.activity.BaseActivity;
import net.maker.commonframework.base.fragment.BaseFragment;
import net.maker.commonframework.common.GlobUtils;
import net.maker.commonframework.network.BaseHttp;
import net.maker.commonframework.network.RunApiService;

import rx.Observable;

/**
 * Created by MakerYan on 16/4/6 11:54.
 * Email: yanl@59store.com
 */
public interface MvpView {

    public <T> Observable.Transformer<T, T> bindToLifecycle();

    /**
     * @param text ProgressDialog Content
     */
    void showProgressDialog(String text);

    /**
     * 关闭ProgressDialog
     */
    void dismissProgressDialog();

    /**
     * 正在加载
     */
    void showLoading(View view);

    /**
     * 加载错误
     */
    void showError(View view);

    /**
     * 加载完成
     */
    void loadingComplete(View view);

    /**
     * 加载完成,显示内容
     */
    void showContent();

    /**
     * @return 获取Presenter
     */

    BaseActivity getBaseActivity();

    Activity getCurrentActivity();

    Picasso getPicasso();

    Gson getGson();

    SharedPreferences getSharedPreferences();

    RunApiService getRunApiService();

    BaseHttp getBaseHttp();

    GlobUtils getGlobUtils();

    /**
     * @return 获取FragmentManager
     */
    FragmentManager getFM();

    /**
     * @param clazz 启动另一个activity并finish
     */
    void startToActivity(Class<?> clazz, Bundle extras);

    /**
     * @param clazz 启动另一个activity并finish
     */
    void startToActivityAndFinish(Class<?> clazz, Bundle extras);

    /**
     * @param layoutResId 容器ID
     * @param fragment    需要添加的Fragment
     */
    void addFragment(int layoutResId, BaseFragment fragment, boolean isAddToBackStack);

    /**
     * @param layoutResId 容器ID
     * @param fragment    需要替换的Fragment
     */
    void replaceFragment(int layoutResId, BaseFragment fragment, boolean isAddToBackStack);
}
