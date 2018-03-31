package com.iptv.event;

/**
 * 有参数无返回值的事件
 */
public abstract class PEvent<Params> extends IEvent {
    public PEvent(String fName) {
        super(fName);
    }

    /**
     * 有参数无返回值的事件
     * @param params 发送事件的参数
     */
    public abstract void event(Params params);
}
