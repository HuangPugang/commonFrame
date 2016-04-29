package net.maker.commonframework.network;


import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by MakerYan on 16/3/4 15:32.
 * Email: yanl@59store.com
 */
public interface APIService {

    /**
     * post请求登陆
     *
     * @param params
     * @return
     */
    @POST("{path}")
    <T> Observable<T> requestByPOST(@FieldMap Map<String, String> params);

    /**
     * post请求登陆
     *
     * @param params
     * @return
     */
    @GET("{path}")
    <T> Observable<T> requestByGET(@QueryMap Map<String, String> params);

}
