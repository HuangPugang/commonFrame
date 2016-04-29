package net.maker.commonframework.network;

import net.maker.commonframework.base.activity.BaseActivity;
import net.maker.commonframework.base.mvp.view.MvpView;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class LogInterceptor implements Interceptor {


    protected RunApiService mRunApiService;
    private BaseActivity mActivity;
    protected MvpView mView;

    public LogInterceptor(MvpView view) {
        mView = view;
        mActivity =   view.getBaseActivity();
        mRunApiService = view.getRunApiService();
    }

    @Override
    public Response intercept(final Chain chain) throws IOException {
        Request oldRequest = chain.request();
        // 添加新的参数
        HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host())
                .addQueryParameter("token", "token");

        // 新的请求
        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(authorizedUrlBuilder.build())
                .build();

        Response response = chain.proceed(newRequest);
        // 在这里做任何返回数据操作
        // TODO
        return response;
    }

}
