package net.maker.commonframework.base.rx.binding.widget;

import android.support.annotation.NonNull;
import android.widget.SeekBar;
import net.maker.commonframework.base.rx.binding.view.ViewEvent;

public abstract class SeekBarChangeEvent extends ViewEvent<SeekBar> {
  SeekBarChangeEvent(@NonNull SeekBar view) {
    super(view);
  }
}
