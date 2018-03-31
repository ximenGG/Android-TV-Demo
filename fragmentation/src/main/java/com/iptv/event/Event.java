package com.iptv.event;

/**
 * 无参数无返回的事件
 */
public abstract class Event extends IEvent {
    public Event(String fName) {
        super(fName);
    }

    /**
     * 无参数无返回的事件
     */
    public abstract void event();
}
