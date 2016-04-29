package net.maker.commonframework.base.mvp.view;


import net.maker.commonframework.base.dagger.component.BaseActivityComponent;
import net.maker.commonframework.base.rx.lifecycle.ActivityEvent;

import rx.Observable;


/**
 * Created by MakerYan on 16/4/1 10:59.
 * Email: yanl@59store.com
 */
public interface IBaseActivityView extends MvpView{

    Observable<ActivityEvent> lifecycle();

    <T> Observable.Transformer<T, T> bindUntilEvent(ActivityEvent event);


    /**
     * @return BaseActivityComponent
     */
    BaseActivityComponent getBaseComponent();

}
