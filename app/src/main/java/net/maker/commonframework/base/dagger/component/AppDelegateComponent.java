package net.maker.commonframework.base.dagger.component;


import net.maker.commonframework.base.BaseApplication;
import net.maker.commonframework.base.dagger.graph.AppDelegateGraph;
import net.maker.commonframework.base.dagger.module.AppDelegateModule;

import dagger.Component;

/**
 * Created by MakerYan on 16/3/31 16:32.
 * Email: yanl@59store.com
 */
@Component(modules = AppDelegateModule.class)
public interface AppDelegateComponent extends AppDelegateGraph {


    final class Initializer {
        private Initializer() {
        }

        public static AppDelegateComponent init(BaseApplication app) {
            return DaggerAppDelegateComponent.builder()
//                    .appDelegateModule(new AppDelegateModule(app))
                    .appDelegateModule(new AppDelegateModule(app))
                    .build();
        }
    }
}
