package net.maker.commonframework.base.rx.binding.support.design.widget;

import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import net.maker.commonframework.base.rx.binding.support.design.widget.TabLayoutSelectionEvent.Kind;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

import static net.maker.commonframework.base.rx.binding.internal.Preconditions.checkUiThread;

final class TabLayoutSelectionEventOnSubscribe
    implements Observable.OnSubscribe<TabLayoutSelectionEvent> {
  final TabLayout view;

  TabLayoutSelectionEventOnSubscribe(TabLayout view) {
    this.view = view;
  }

  @Override public void call(final Subscriber<? super TabLayoutSelectionEvent> subscriber) {
    checkUiThread();

    final TabLayout.OnTabSelectedListener listener = new TabLayout.OnTabSelectedListener() {
      @Override public void onTabSelected(Tab tab) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(TabLayoutSelectionEvent.create(view, Kind.SELECTED, tab));
        }
      }

      @Override public void onTabUnselected(Tab tab) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(TabLayoutSelectionEvent.create(view, Kind.UNSELECTED, tab));
        }
      }

      @Override public void onTabReselected(Tab tab) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(TabLayoutSelectionEvent.create(view, Kind.RESELECTED, tab));
        }
      }
    };
    view.setOnTabSelectedListener(listener);

    subscriber.add(new MainThreadSubscription() {
      @Override protected void onUnsubscribe() {
        view.setOnTabSelectedListener(null);
      }
    });

    int index = view.getSelectedTabPosition();
    if (index != -1) {
      subscriber.onNext(TabLayoutSelectionEvent.create(view, Kind.SELECTED, view.getTabAt(index)));
    }
  }
}
