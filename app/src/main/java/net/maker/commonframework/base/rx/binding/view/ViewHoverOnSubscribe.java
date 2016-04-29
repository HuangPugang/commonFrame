package net.maker.commonframework.base.rx.binding.view;

import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;
import rx.functions.Func1;

import static net.maker.commonframework.base.rx.binding.internal.Preconditions.checkUiThread;

final class ViewHoverOnSubscribe implements Observable.OnSubscribe<MotionEvent> {
  final View view;
  final Func1<? super MotionEvent, Boolean> handled;

  ViewHoverOnSubscribe(View view, Func1<? super MotionEvent, Boolean> handled) {
    this.view = view;
    this.handled = handled;
  }

  @Override public void call(final Subscriber<? super MotionEvent> subscriber) {
    checkUiThread();

    View.OnHoverListener listener = new View.OnHoverListener() {
      @Override public boolean onHover(View v, @NonNull MotionEvent event) {
        if (handled.call(event)) {
          if (!subscriber.isUnsubscribed()) {
            subscriber.onNext(event);
          }
          return true;
        }
        return false;
      }
    };
    view.setOnHoverListener(listener);

    subscriber.add(new MainThreadSubscription() {
      @Override protected void onUnsubscribe() {
        view.setOnHoverListener(null);
      }
    });
  }
}
