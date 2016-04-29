package net.maker.commonframework.base.rx.binding.widget;

import android.support.annotation.NonNull;
import android.widget.AdapterView;
import net.maker.commonframework.base.rx.binding.view.ViewEvent;

public abstract class AdapterViewSelectionEvent extends ViewEvent<AdapterView<?>> {
  AdapterViewSelectionEvent(@NonNull AdapterView<?> view) {
    super(view);
  }
}
