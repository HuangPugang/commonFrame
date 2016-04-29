package net.maker.commonframework.base.mvp.presenter;


import net.maker.commonframework.base.mvp.view.MvpView;

/**
 * Created by MakerYan on 16/4/4 10:45.
 * Email: yanl@59store.com
 */
public interface IBasePresenter<V extends MvpView> {

    /**
     * Set or attach the view to this presenter
     */
    void attachView(V view);

    /**
     * Will be called if the view has been destroyed. Typically this method will be invoked from
     * <code>Activity.detachView()</code> or <code>Fragment.onDestroyView()</code>
     */
    void detachView(boolean retainInstance);

    void resume();

    void pause();

    void stop();

    void destroy();
}
