package net.maker.commonframework.base.rx.binding.view;

import android.annotation.TargetApi;
import android.view.View;
import android.view.ViewTreeObserver;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

import static net.maker.commonframework.base.rx.binding.internal.Preconditions.checkUiThread;
import static android.os.Build.VERSION_CODES.JELLY_BEAN;

@TargetApi(JELLY_BEAN)
final class ViewTreeObserverDrawOnSubscribe implements Observable.OnSubscribe<Void> {
  final View view;

  ViewTreeObserverDrawOnSubscribe(View view) {
    this.view = view;
  }

  @Override public void call(final Subscriber<? super Void> subscriber) {
    checkUiThread();

    final ViewTreeObserver.OnDrawListener listener = new ViewTreeObserver.OnDrawListener() {
      @Override public void onDraw() {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(null);
        }
      }
    };

    view.getViewTreeObserver().addOnDrawListener(listener);

    subscriber.add(new MainThreadSubscription() {
      @Override protected void onUnsubscribe() {
        view.getViewTreeObserver().removeOnDrawListener(listener);
      }
    });
  }
}
