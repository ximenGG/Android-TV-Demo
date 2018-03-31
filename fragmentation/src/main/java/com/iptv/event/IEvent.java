package com.iptv.event;

/**
 * 所有事件的基类，约束每个事件都有一个标记
 */
public abstract class IEvent {
    /**
     * 发送事件的名字
     */
    public String fName;
    public IEvent(String fName) {
        this.fName = fName;
    }
}
