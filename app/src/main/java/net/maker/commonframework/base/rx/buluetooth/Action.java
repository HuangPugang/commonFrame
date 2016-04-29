package net.maker.commonframework.base.rx.buluetooth;

import rx.functions.Func1;

/**
 * Created by MakerYan on 16/4/9 22:50.
 * Email : yanl@59store.com
 * Personal e-mail : yl_flash@163.com
 * project name : CommonFramework
 * package name : net.maker.commonframework.base.rx.buluetooth
 */
public class Action {
    /**
     * Creates a function, which checks if current action equals single action or one of many
     * actions. It can be used inside filter(...) method from RxJava.
     *
     * @param actions many actions or single action
     * @return Func1 checking function
     */
    public static <T> Func1<T, Boolean> isEqualTo(final T... actions) {
        return new Func1<T, Boolean>() {
            @Override public Boolean call(T action) {
                for (T t : actions) {
                    if (t.equals(action)) {
                        return true;
                    }
                }
                return false;
            }
        };
    }
}
