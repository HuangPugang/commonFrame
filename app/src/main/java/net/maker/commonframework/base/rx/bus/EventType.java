package net.maker.commonframework.base.rx.bus;

/**
 * Created by MakerYan on 16/4/10 10:17.
 * Email : yanl@59store.com
 * Personal e-mail : yl_flash@163.com
 * project name : CommonFramework
 * package name : net.maker.commonframework.base.rx.bus
 */
public class EventType<T> {
    private long EventCode;

    private T t;

    public EventType() {
    }

    public EventType(T t) {
        this.t = t;
    }

    public EventType(long eventCode) {
        EventCode = eventCode;
    }

    public EventType(long eventCode, T t) {
        EventCode = eventCode;
        this.t = t;
    }

    public long getEventCode() {
        return EventCode;
    }

    public void setEventCode(long eventCode) {
        EventCode = eventCode;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
