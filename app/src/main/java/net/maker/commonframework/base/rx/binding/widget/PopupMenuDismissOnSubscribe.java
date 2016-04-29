package net.maker.commonframework.base.rx.binding.widget;

import android.widget.PopupMenu;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

import static net.maker.commonframework.base.rx.binding.internal.Preconditions.checkUiThread;

final class PopupMenuDismissOnSubscribe implements Observable.OnSubscribe<Void> {

  final PopupMenu view;

  public PopupMenuDismissOnSubscribe(PopupMenu view) {
    this.view = view;
  }

  @Override public void call(final Subscriber<? super Void> subscriber) {
    checkUiThread();

    PopupMenu.OnDismissListener listener = new PopupMenu.OnDismissListener() {
      @Override public void onDismiss(PopupMenu menu) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(null);
        }
      }
    };

    view.setOnDismissListener(listener);

    subscriber.add(new MainThreadSubscription() {
      @Override protected void onUnsubscribe() {
        view.setOnDismissListener(null);
      }
    });
  }
}
