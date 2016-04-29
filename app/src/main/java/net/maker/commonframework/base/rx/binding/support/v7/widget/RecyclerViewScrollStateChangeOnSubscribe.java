package net.maker.commonframework.base.rx.binding.support.v7.widget;

import android.support.v7.widget.RecyclerView;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

import static net.maker.commonframework.base.rx.binding.internal.Preconditions.checkUiThread;

final class RecyclerViewScrollStateChangeOnSubscribe implements Observable.OnSubscribe<Integer> {
  final RecyclerView recyclerView;

  public RecyclerViewScrollStateChangeOnSubscribe(RecyclerView recyclerView) {
    this.recyclerView = recyclerView;
  }

  @Override public void call(final Subscriber<? super Integer> subscriber) {
    checkUiThread();

    final RecyclerView.OnScrollListener listener = new RecyclerView.OnScrollListener() {
      @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(newState);
        }
      }
    };
    recyclerView.addOnScrollListener(listener);

    subscriber.add(new MainThreadSubscription() {
      @Override protected void onUnsubscribe() {
        recyclerView.removeOnScrollListener(listener);
      }
    });
  }
}
