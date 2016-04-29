package net.maker.commonframework.base;


import android.support.multidex.MultiDexApplication;

import net.maker.commonframework.base.dagger.component.AppDelegateComponent;

/**
 * Created by MakerYan on 16/4/8 22:32.
 * Email: yanl@59store.com
 */
public class BaseApplication extends MultiDexApplication {

    protected static BaseApplication mApplication;
    protected static AppDelegateComponent mAppDelegateComponent;


    public static BaseApplication getApplication() {
        return mApplication;
    }

    public static AppDelegateComponent component() {
        return mAppDelegateComponent;
    }

    public static void buildComponent() {
        mAppDelegateComponent = AppDelegateComponent.Initializer.init(mApplication);
    }

    public static BaseApplication newInstance() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        buildComponent();
    }

}