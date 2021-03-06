package net.maker.commonframework.base.rx.binding.support.v7.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import rx.Observable;

import static net.maker.commonframework.base.rx.binding.internal.Preconditions.checkNotNull;

/** Static factory methods for creating {@linkplain Observable observables} for {@link Adapter}. */
public final class RxRecyclerViewAdapter {
  /**
   * Create an observable of data change events for {@code RecyclerView.adapter}.
   * <p>
   * <em>Note:</em> A value will be emitted immediately on subscribe.
   */
  @CheckResult @NonNull
  public static <T extends Adapter<? extends ViewHolder>> Observable<T> dataChanges(
      @NonNull T adapter) {
    checkNotNull(adapter, "adapter == null");
    return Observable.create(new RecyclerAdapterDataChangeOnSubscribe<>(adapter));
  }

  private RxRecyclerViewAdapter() {
    throw new AssertionError("No instances.");
  }
}
