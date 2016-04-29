package net.maker.commonframework.base.dagger.module;


import net.maker.commonframework.base.BaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MakerYan on 16/4/1 16:58.
 * Email: yanl@59store.com
 */
@Module
public class AppDelegateModule {
    private final BaseApplication mAppDelegate;

    public AppDelegateModule(BaseApplication appDelegate) {
        mAppDelegate = appDelegate;
    }

    @Singleton
    @Provides
    public BaseApplication providesApp() {
        return mAppDelegate;
    }
}
