package net.maker.commonframework.base.rx.binding.view;

import android.view.DragEvent;
import android.view.View;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;
import rx.functions.Func1;

import static net.maker.commonframework.base.rx.binding.internal.Preconditions.checkUiThread;

final class ViewDragOnSubscribe implements Observable.OnSubscribe<DragEvent> {
  final View view;
  final Func1<? super DragEvent, Boolean> handled;

  ViewDragOnSubscribe(View view, Func1<? super DragEvent, Boolean> handled) {
    this.view = view;
    this.handled = handled;
  }

  @Override public void call(final Subscriber<? super DragEvent> subscriber) {
    checkUiThread();

    View.OnDragListener listener = new View.OnDragListener() {
      @Override public boolean onDrag(View v, DragEvent event) {
        if (handled.call(event)) {
          if (!subscriber.isUnsubscribed()) {
            subscriber.onNext(event);
          }
          return true;
        }
        return false;
      }
    };
    view.setOnDragListener(listener);

    subscriber.add(new MainThreadSubscription() {
      @Override protected void onUnsubscribe() {
        view.setOnDragListener(null);
      }
    });
  }
}
