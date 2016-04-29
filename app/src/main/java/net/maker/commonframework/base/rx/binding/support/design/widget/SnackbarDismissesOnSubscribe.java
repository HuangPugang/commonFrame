package net.maker.commonframework.base.rx.binding.support.design.widget;

import android.support.design.widget.Snackbar;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

import static net.maker.commonframework.base.rx.binding.internal.Preconditions.checkUiThread;

final class SnackbarDismissesOnSubscribe implements Observable.OnSubscribe<Integer> {
  final Snackbar view;

  SnackbarDismissesOnSubscribe(Snackbar view) {
    this.view = view;
  }

  @Override public void call(final Subscriber<? super Integer> subscriber) {
    checkUiThread();

    Snackbar.Callback callback = new Snackbar.Callback() {
      @Override public void onDismissed(Snackbar snackbar, int event) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(event);
        }
      }
    };

    view.setCallback(callback);

    subscriber.add(new MainThreadSubscription() {
      @Override protected void onUnsubscribe() {
        view.setCallback(null);
      }
    });
  }
}
