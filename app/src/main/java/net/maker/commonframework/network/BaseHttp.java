package net.maker.commonframework.network;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MakerYan on 16/3/4 14:31.
 * Email: yanl@59store.com
 */
public class BaseHttp {

    protected LogInterceptor mLogInterceptor;

    public BaseHttp(LogInterceptor logInterceptor) {
        mLogInterceptor = logInterceptor;
    }

    public APIService getAPIService() {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(mLogInterceptor) // 添加拦截器
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("API.SERVER_URL") // 填入正确的URL
                .addConverterFactory(GsonConverterFactory.create()) // 解决适配器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //
                .client(client)
                .build();

        return retrofit.create(APIService.class);
    }

}
