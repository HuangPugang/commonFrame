package net.maker.commonframework.base.mvp.view;


import net.maker.commonframework.base.rx.lifecycle.FragmentEvent;

import rx.Observable;

/**
 * Created by MakerYan on 16/4/6 11:54.
 * Email: yanl@59store.com
 */
public interface IBaseFragmentView extends MvpView {
    public Observable<FragmentEvent> lifecycle();

    public <T> Observable.Transformer<T, T> bindUntilEvent(FragmentEvent event);

    MvpView getMvpView();
}
