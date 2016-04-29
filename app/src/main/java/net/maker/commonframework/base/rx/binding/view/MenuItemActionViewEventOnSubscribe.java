package net.maker.commonframework.base.rx.binding.view;

import android.view.MenuItem;
import net.maker.commonframework.base.rx.binding.view.MenuItemActionViewEvent.Kind;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;
import rx.functions.Func1;

import static net.maker.commonframework.base.rx.binding.internal.Preconditions.checkUiThread;

final class MenuItemActionViewEventOnSubscribe
    implements Observable.OnSubscribe<MenuItemActionViewEvent> {
  final MenuItem menuItem;
  final Func1<? super MenuItemActionViewEvent, Boolean> handled;

  MenuItemActionViewEventOnSubscribe(MenuItem menuItem,
      Func1<? super MenuItemActionViewEvent, Boolean> handled) {
    this.menuItem = menuItem;
    this.handled = handled;
  }

  @Override public void call(final Subscriber<? super MenuItemActionViewEvent> subscriber) {
    checkUiThread();

    MenuItem.OnActionExpandListener listener = new MenuItem.OnActionExpandListener() {
      @Override public boolean onMenuItemActionExpand(MenuItem item) {
        MenuItemActionViewEvent event = MenuItemActionViewEvent.create(menuItem, Kind.EXPAND);
        return onEvent(event);
      }

      @Override public boolean onMenuItemActionCollapse(MenuItem item) {
        MenuItemActionViewEvent event = MenuItemActionViewEvent.create(menuItem, Kind.COLLAPSE);
        return onEvent(event);
      }

      private boolean onEvent(MenuItemActionViewEvent event) {
        if (handled.call(event)) {
          if (!subscriber.isUnsubscribed()) {
            subscriber.onNext(event);
          }
          return true;
        }
        return false;
      }
    };

    menuItem.setOnActionExpandListener(listener);

    subscriber.add(new MainThreadSubscription() {
      @Override protected void onUnsubscribe() {
        menuItem.setOnActionExpandListener(null);
      }
    });
  }
}
