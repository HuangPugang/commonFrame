package net.maker.commonframework.base.rx.binding.support.design.widget;

import android.support.design.widget.AppBarLayout;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

import static net.maker.commonframework.base.rx.binding.internal.Preconditions.checkUiThread;

final class AppBarLayoutOffsetChangeOnSubscribe implements Observable.OnSubscribe<Integer> {
  final AppBarLayout view;

  AppBarLayoutOffsetChangeOnSubscribe(AppBarLayout view) {
    this.view = view;
  }

  @Override public void call(final Subscriber<? super Integer> subscriber) {
    checkUiThread();

    final AppBarLayout.OnOffsetChangedListener listener =
        new AppBarLayout.OnOffsetChangedListener() {
          @Override public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            if (!subscriber.isUnsubscribed()) {
              subscriber.onNext(verticalOffset);
            }
          }
        };
    view.addOnOffsetChangedListener(listener);

    subscriber.add(new MainThreadSubscription() {
      @Override protected void onUnsubscribe() {
        view.removeOnOffsetChangedListener(listener);
      }
    });
  }
}
