package net.maker.commonframework.base.mvp.presenter;

import android.support.annotation.Nullable;

import net.maker.commonframework.base.mvp.view.MvpView;

import java.lang.ref.WeakReference;

import de.greenrobot.event.EventBus;

/**
 * Created by MakerYan on 16/4/4 10:46.
 * Email: yanl@59store.com
 */
public abstract class BasePresetner<V extends MvpView> implements IBasePresenter<V> {


    private WeakReference<V> viewRef;

    public BasePresetner(V v) {
        attachView(v);
        if (isBindEventBusHere()) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void attachView(V view) {
        viewRef = new WeakReference<V>(view);
    }

    /**
     * Get the attached view. You should always call {@link #isViewAttached()} to check if the view
     * is
     * attached to avoid NullPointerExceptions.
     *
     * @return <code>null</code>, if view is not attached, otherwise the concrete view instance
     */
    @Nullable
    public V getView() {
        return viewRef == null ? null : viewRef.get();
    }

    /**
     * Checks if a view is attached to this presenter. You should always call this method before
     * calling {@link #getView()} to get the view instance.
     */
    public boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    @Override
    public void detachView(boolean retainInstance) {
        if (isBindEventBusHere()) {
            EventBus.getDefault().unregister(this);
        }
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }

    /**
     * @return 是否在此绑定EventBus
     */
    protected abstract boolean isBindEventBusHere();
}
