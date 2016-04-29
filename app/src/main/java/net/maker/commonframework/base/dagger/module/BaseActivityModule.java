package net.maker.commonframework.base.dagger.module;

import android.app.Activity;
import android.content.res.Resources;

import com.alipay.euler.andfix.patch.PatchManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import net.maker.commonframework.base.BaseApplication;
import net.maker.commonframework.base.activity.BaseActivity;
import net.maker.commonframework.base.mvp.view.MvpView;
import net.maker.commonframework.common.GlobUtils;
import net.maker.commonframework.common.StatusBarUtil;
import net.maker.commonframework.network.BaseHttp;
import net.maker.commonframework.network.LogInterceptor;
import net.maker.commonframework.network.RunApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MakerYan on 16/4/4 22:26.
 * Email: yanl@59store.com
 */
@Module
public class BaseActivityModule {
    protected final MvpView mView;
    protected final LogInterceptor mLogInterceptor;
    protected final BaseHttp mBaseHttp;
    protected final RunApiService mRunApiService;
    private final BaseActivity mBaseActivity;
    private final BaseApplication context;


    public BaseActivityModule(BaseActivity baseActivity, MvpView view) {
        mBaseActivity = baseActivity;
        mView = view;
        context = (BaseApplication) mBaseActivity.getApplicationContext();
        mLogInterceptor = new LogInterceptor(view);
        mBaseHttp = new BaseHttp(mLogInterceptor);
        mRunApiService = new RunApiService(mBaseHttp);
    }

    @Singleton
    @Provides
    public MvpView providesView() {
        return mView;
    }

    @Singleton
    @Provides
    public Activity providesActivity() {
        return mBaseActivity;
    }

    @Singleton
    @Provides
    public BaseActivity providesBaseActivity() {
        return mBaseActivity;
    }

    @Singleton
    @Provides
    public Picasso providesPicasso() {
        return Picasso.with(context);
    }

    @Singleton
    @Provides
    public Resources providesResources() {
        return mBaseActivity.getResources();
    }


    @Singleton
    @Provides
    public Gson providesGson() {
        return new GsonBuilder().create();
    }


    @Singleton
    @Provides
    public BaseHttp providesBaseHttp() {
        return mBaseHttp;
    }


    @Singleton
    @Provides
    public RunApiService providesRunApiService() {
        return mRunApiService;
    }

    @Singleton
    @Provides
    public LogInterceptor providesLogInterceptor() {
        return mLogInterceptor;
    }

    @Singleton
    @Provides
    public GlobUtils providesGlobUtils() {
        return new GlobUtils(mView);
    }

    @Singleton
    @Provides
    public StatusBarUtil providesStatusBarUtil() {
        return new StatusBarUtil(mView);
    }
}
