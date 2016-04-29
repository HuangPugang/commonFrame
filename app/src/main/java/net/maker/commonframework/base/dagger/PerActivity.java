package net.maker.commonframework.base.dagger;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by MakerYan on 16/4/4 22:48.
 * Email: yanl@59store.com
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
