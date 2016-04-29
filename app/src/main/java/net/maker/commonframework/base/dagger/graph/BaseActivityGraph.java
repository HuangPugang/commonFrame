package net.maker.commonframework.base.dagger.graph;


import net.maker.commonframework.base.activity.BaseActivity;
import net.maker.commonframework.base.fragment.BaseDialogFragment;
import net.maker.commonframework.base.fragment.BaseFragment;
import net.maker.commonframework.base.view.BaseLinearLayout;
import net.maker.commonframework.base.view.BaseRelativeLayout;
import net.maker.commonframework.base.view.BaseView;

/**
 * Created by MakerYan on 16/4/4 22:27.
 * Email: yanl@59store.com
 */
public interface BaseActivityGraph {
    BaseActivity inject(BaseActivity activity);
    BaseFragment inject(BaseFragment fragment);
    BaseView inject(BaseView view);
    BaseRelativeLayout inject(BaseRelativeLayout viewGroup);
    BaseLinearLayout inject(BaseLinearLayout viewGroup);
    BaseDialogFragment inject(BaseDialogFragment dialogFragment);
}
