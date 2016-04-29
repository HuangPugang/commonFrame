package net.maker.commonframework.base.rx.binding.support.v7.widget;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnChildAttachStateChangeListener;
import android.view.View;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

import static net.maker.commonframework.base.rx.binding.internal.Preconditions.checkUiThread;

final class RecyclerViewChildAttachStateChangeEventOnSubscribe
    implements Observable.OnSubscribe<RecyclerViewChildAttachStateChangeEvent> {
  final RecyclerView recyclerView;

  public RecyclerViewChildAttachStateChangeEventOnSubscribe(RecyclerView recyclerView) {
    this.recyclerView = recyclerView;
  }

  @Override
  public void call(final Subscriber<? super RecyclerViewChildAttachStateChangeEvent> subscriber) {
    checkUiThread();

    final OnChildAttachStateChangeListener listener = new OnChildAttachStateChangeListener() {
      @Override public void onChildViewAttachedToWindow(View childView) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(RecyclerViewChildAttachEvent.create(recyclerView, childView));
        }
      }

      @Override public void onChildViewDetachedFromWindow(View childView) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(RecyclerViewChildDetachEvent.create(recyclerView, childView));
        }
      }
    };

    recyclerView.addOnChildAttachStateChangeListener(listener);

    subscriber.add(new MainThreadSubscription() {
      @Override protected void onUnsubscribe() {
        recyclerView.removeOnChildAttachStateChangeListener(listener);
      }
    });
  }
}
