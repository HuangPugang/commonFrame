package net.maker.commonframework.base.rx.lifecycle;

import rx.Observable;

/**
 * Created by MakerYan on 16/4/8 23:46.
 * Email: yanl@59store.com
 */
public interface FragmentLifecycleProvider {

    /**
     * @return a sequence of {@link android.app.Fragment} lifecycle events
     */
    Observable<FragmentEvent> lifecycle();

    /**
     * Binds a source until a specific {@link FragmentEvent} occurs.
     * <p>
     * Intended for use with {@link Observable#compose(Observable.Transformer)}
     *
     * @param event the {@link FragmentEvent} that triggers unsubscription
     * @return a reusable {@link rx.Observable.Transformer} which unsubscribes when the event triggers.
     */
    <T> Observable.Transformer<T, T> bindUntilEvent(FragmentEvent event);

    /**
     * Binds a source until the next reasonable {@link FragmentEvent} occurs.
     * <p>
     * Intended for use with {@link Observable#compose(Observable.Transformer)}
     *
     * @return a reusable {@link rx.Observable.Transformer} which unsubscribes at the correct time.
     */
    <T> Observable.Transformer<T, T> bindToLifecycle();

}
