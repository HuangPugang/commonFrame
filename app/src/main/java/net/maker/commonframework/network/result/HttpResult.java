package net.maker.commonframework.network.result;

import java.io.Serializable;

/**
 * Created by MakerYan on 16/4/10 19:13.
 * Email : yanl@59store.com
 * Personal e-mail : yl_flash@163.com
 * project name : CommonFramework
 * package name : net.maker.commonframework.network.result
 */
public class HttpResult<T> implements Serializable {
    private int status;
    private String msg;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

}
