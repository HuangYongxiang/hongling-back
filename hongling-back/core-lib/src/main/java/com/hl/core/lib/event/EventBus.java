package com.hl.core.lib.event;

/**
 * 跨界面、跨线程通讯工具，可携带数据对象<br>
 * 文档：https://github.com/greenrobot/EventBus
 * @Package com.xunlei.video.framework.messager
 * @ClassName: Event
 * @author Beethoven
 * @mail zhanghuitao@xunlei.com
 * @date Apr 16, 2014 6:22:35 PM
 */
public class EventBus {

    /**
     * 发送应用内事件消息
     * @Title: post
     * @param message
     * @return void
     * @date Apr 16, 2014 6:18:59 PM
     */
    public static void post(Object message) {
        org.greenrobot.eventbus.EventBus.getDefault().post(message);
    }

    /**
     * 注册java类接收应用内事件消息
     * @Title: register
     * @param subscriber
     * @return void
     * @date Apr 16, 2014 6:19:18 PM
     */
    public static void register(Object subscriber) {
        org.greenrobot.eventbus.EventBus.getDefault().register(subscriber);
    }

    /**
     * 注销java类接收应用内事件消息
     * @Title: unregister
     * @param subscriber
     * @return void
     * @date Apr 16, 2014 6:19:42 PM
     */
    public static void unregister(Object subscriber) {
        org.greenrobot.eventbus.EventBus.getDefault().unregister(subscriber);
    }

}
