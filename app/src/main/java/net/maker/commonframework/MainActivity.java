package net.maker.commonframework;

import android.os.Bundle;
import android.view.View;

import net.maker.commonframework.base.activity.BaseActivity;
import net.maker.commonframework.base.mvp.presenter.BasePresetner;

/**
 * Created by MakerYan on 16/4/8 22:32.
 * Email: yanl@59store.com
 */
public class MainActivity extends BaseActivity{

    /**
     * @return 布局Id
     */
    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    /**
     * @param extras 上层传过来的数据
     */
    @Override
    protected void getExtras(Bundle extras) {

    }

    /**
     * 初始Dagger
     */
    @Override
    protected void initializerComponent() {

    }

    /**
     * @return 是否绑定EventBus?
     */
    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    /**
     * 获取控件
     */
    @Override
    protected void assignViews() {

    }

    /**
     * 控件赋值
     *
     * @param savedInstanceState
     */
    @Override
    protected void initWidget(Bundle savedInstanceState) {

    }

    /**
     * 注册控件监听
     */
    @Override
    protected void registListener() {

    }

    /**
     * @return 在哪个布局上显示loading
     */
    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    /**
     * 开始处理
     */
    @Override
    protected void doAction() {

    }

    /**
     * @return 获取当前Presenter
     */
    @Override
    protected <P extends BasePresetner> P getPresenter() {
        return null;
    }
}
