package com.iptv.event;

/**
 * 有参数和返回值的事件
 */
public abstract class PREvent<Result, Params> extends IEvent {
    public PREvent(String fName) {
        super(fName);
    }

    /**
     * 有参数和返回值的事件
     * @param params 发送事件的参数
     * @return
     */
    public abstract Result event(Params params);
}
