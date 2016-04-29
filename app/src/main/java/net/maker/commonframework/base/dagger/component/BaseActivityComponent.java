package net.maker.commonframework.base.dagger.component;


import net.maker.commonframework.base.BaseApplication;
import net.maker.commonframework.base.activity.BaseActivity;
import net.maker.commonframework.base.dagger.graph.BaseActivityGraph;
import net.maker.commonframework.base.dagger.module.BaseActivityModule;
import net.maker.commonframework.base.mvp.view.IBaseActivityView;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by MakerYan on 16/4/4 22:23.
 * Email: yanl@59store.com
 */
@Singleton
@Component(dependencies = {AppDelegateComponent.class}, modules = {BaseActivityModule.class})
public interface BaseActivityComponent extends BaseActivityGraph {

    final class Initializer {
        public static BaseActivityComponent init(BaseActivity activity, IBaseActivityView view) {
            return DaggerBaseActivityComponent.builder()
                    .appDelegateComponent(BaseApplication.component())
                    .baseActivityModule(new BaseActivityModule(activity, view))
                    .build();
        }

    }

}
