package com.iptv.event;

/**
 * 有返回值无参数的事件
 */
public abstract class REvent<Result> extends IEvent {
    public REvent(String fName) {
        super(fName);
    }

    /**
     * 有返回值无参数的事件
     * @return
     */
    public abstract Result event();
}
