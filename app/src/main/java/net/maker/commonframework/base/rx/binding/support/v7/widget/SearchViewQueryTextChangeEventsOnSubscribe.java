package net.maker.commonframework.base.rx.binding.support.v7.widget;

import android.support.v7.widget.SearchView;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

import static net.maker.commonframework.base.rx.binding.internal.Preconditions.checkUiThread;

final class SearchViewQueryTextChangeEventsOnSubscribe
    implements Observable.OnSubscribe<SearchViewQueryTextEvent> {
  final SearchView view;

  SearchViewQueryTextChangeEventsOnSubscribe(SearchView view) {
    this.view = view;
  }

  @Override public void call(final Subscriber<? super SearchViewQueryTextEvent> subscriber) {
    checkUiThread();

    SearchView.OnQueryTextListener watcher = new SearchView.OnQueryTextListener() {
      @Override public boolean onQueryTextChange(String s) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(SearchViewQueryTextEvent.create(view, s, false));
          return true;
        }
        return false;
      }

      @Override public boolean onQueryTextSubmit(String query) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(SearchViewQueryTextEvent.create(view, view.getQuery(), true));
          return true;
        }
        return false;
      }
    };

    view.setOnQueryTextListener(watcher);

    subscriber.add(new MainThreadSubscription() {
      @Override protected void onUnsubscribe() {
        view.setOnQueryTextListener(null);
      }
    });

    // Emit initial value.
    subscriber.onNext(SearchViewQueryTextEvent.create(view, view.getQuery(), false));
  }
}