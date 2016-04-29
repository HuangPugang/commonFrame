package net.maker.commonframework.network;


import net.maker.commonframework.base.mvp.model.BaseViewModel;
import net.maker.commonframework.common.entity.HttpRequest;

import java.util.Map;

import rx.Observable;

/**
 * Created by MakerYan on 16/4/5 17:00.
 * Email: yanl@59store.com
 */
public class RunApiService implements BaseViewModel {


    protected BaseHttp mBaseHttp;

    public RunApiService(BaseHttp baseHttp) {
        mBaseHttp = baseHttp;
    }

    /**
     * @param params 请求参数
     * @return 请求结果
     */
    public Observable<HttpRequest> requestByGET(Map<String, String> params) {
        return mBaseHttp.getAPIService().requestByGET(params);
    }

    /**
     * @param params 请求参数
     * @return 请求结果
     */
    public Observable<HttpRequest> requestByPOST(Map<String, String> params) {
        return mBaseHttp.getAPIService().requestByPOST(params);
    }
}
