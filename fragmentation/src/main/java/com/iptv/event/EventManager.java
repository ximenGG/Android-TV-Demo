package com.iptv.event;

import android.text.TextUtils;

import java.util.HashMap;

/**
 * 事件管理器
 */
public class EventManager {
    private static EventManager instance = null;
    /**
     * 缓存有返回值有参数的集合
     */
    private HashMap<String, PREvent> prEvents = new HashMap<>();
    /**
     * 缓存无返回值有参数的集合
     */
    private HashMap<String, PEvent> pEvents = new HashMap<>();
    /**
     * 缓存有返回值无参数的集合
     */
    private HashMap<String, REvent> rEvents = new HashMap<>();
    /**
     * 缓存无返回值有无参数的集合
     */
    private HashMap<String, Event> events = new HashMap<>();

    private EventManager() {
        if (null != Instance.instance)
            throw new RuntimeException("no new instance");
    }

    private static class Instance {
        private static final EventManager instance = new EventManager();
    }

    /**
     * 实例化事件管理器
     *
     * @return EventManager
     */
    public static EventManager getInstance() {
        return Instance.instance;
    }

    /**
     * 添加一个有参数和返回值的事件
     *
     * @param prEvent
     * @return
     */
    public EventManager post(PREvent prEvent) {
        prEvents.put(prEvent.fName, prEvent);
        return this;
    }

    /**
     * 添加一个有参数无返回值的事件
     *
     * @param pEvent
     * @return
     */
    public EventManager post(PEvent pEvent) {
        pEvents.put(pEvent.fName, pEvent);
        return this;
    }

    /**
     * 添加一个有返回值无参数的事件
     *
     * @param rEvent
     * @return
     */
    public EventManager post(REvent rEvent) {
        rEvents.put(rEvent.fName, rEvent);
        return this;
    }

    /**
     * 添加一个无参数无返回的事件
     *
     * @param event
     * @return
     */
    public EventManager post(Event event) {
        events.put(event.fName, event);
        return this;
    }

    /**
     * 执行无参数无返回事件并且删除这个事件
     *
     * @param name 事件的名字
     */
    public void invokeEvent(String name) {
        invokeEvent(name, false);
    }

    /**
     * 执行无参数无返回事件
     * @param name   事件的名字
     * @param isSave 是否保存该事件
     */
    public void invokeEvent(String name, boolean isSave) {
        if (events == null || TextUtils.isEmpty(name)) return;
        Event event = events.get(name);
        if (event != null) {
            event.event();
            if (!isSave) {
                events.remove(name);
            }
        } else {
            try {
                throw new EventException("this event is null or remove" + name);
            } catch (EventException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 执行有参数无返回事件并且删除这个事件
     *
     * @param name     事件的名字
     * @param parmas   传递的参数
     * @param <Parmas> 参数的类型
     */
    public <Parmas> void invokeEvent(String name, Parmas parmas) {
        invokeEvent(name, parmas, false);
    }

    /**
     * 执行有参数无返回事件
     * @param name     事件的名字
     * @param parmas   传递的参数
     * @param isSave   是否保存
     * @param <Parmas> 参数的类型
     */
    public <Parmas> void invokeEvent(String name, Parmas parmas, boolean isSave) {
        if (pEvents == null || TextUtils.isEmpty(name)) return;
        PEvent pEvent = pEvents.get(name);
        if (pEvent != null) {
            pEvent.event(parmas);
            if (!isSave) {
                pEvents.remove(name);
            }
        } else {
            try {
                throw new EventException("this event is null or remove" + name);
            } catch (EventException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 执行无参数无返回事件并且删除这个事件
     *
     * @param name     事件名字
     * @param vClass   接收的返回值的类型
     * @param <Result> 返回值的类型
     * @return
     */
    public <Result> Result invokeEvent(String name, Class<Result> vClass) {
        return invokeEvent(name, vClass, false);
    }

    /**
     * 执行无参数有返回事件
     *
     * @param name     事件名字
     * @param vClass   接收的返回值的类型
     * @param isSave   是否保存
     * @param <Result> 返回值的类型
     * @return
     */
    public <Result> Result invokeEvent(String name, Class<Result> vClass, boolean isSave) {
        if (rEvents == null || TextUtils.isEmpty(name)) return null;
        REvent rEvent = rEvents.get(name);

        if (rEvent != null) {
            if (vClass != null) {
                Result result = vClass.cast(rEvent.event());
                if (!isSave) {
                    rEvents.remove(name);
                }
                return result;
            } else {
                Result result = (Result) rEvent.event();
                if (!isSave) {
                    rEvents.remove(name);
                }
                return result;
            }
        } else {
            try {
                throw new EventException("this event is null or remove" + name);
            } catch (EventException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 执行无参数无返回事件并且删除这个事件
     *
     * @param name     事件名字
     * @param vClass   接收的返回值的类型
     * @param parmas   传递的参数
     * @param <Result> 返回值的类型
     * @param <Parmas> 参数的类型
     * @return
     */
    public <Result, Parmas> Result invokeEvent(String name, Class<Result> vClass, Parmas parmas) {
        return invokeEvent(name, vClass, parmas, false);
    }

    /**
     * 执行有参数有返回事件
     *
     * @param name     事件名字
     * @param vClass   接收的返回值的类型
     * @param parmas   传递的参数
     * @param isSave   是否保存
     * @param <Result> 返回值的类型
     * @param <Parmas> 参数的类型
     * @return
     */
    public <Result, Parmas> Result invokeEvent(String name, Class<Result> vClass, Parmas parmas, boolean isSave) {
        if (prEvents == null || TextUtils.isEmpty(name)) return null;
        PREvent prEvent = prEvents.get(name);
        if (prEvent != null) {
            if (vClass != null) {
                Result result = vClass.cast(prEvent.event(parmas));
                if (!isSave) {
                    prEvents.remove(name);
                }
                return result;
            } else {
                Result result = (Result) prEvent.event(parmas);
                if (!isSave) {
                    prEvents.remove(name);
                }
                return result;
            }

        } else {
            try {
                throw new EventException("this event is null or remove" + name);
            } catch (EventException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
