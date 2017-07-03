package cn.cincout.black.hole.doredis.application.listener;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-7-3
 * @sine 1.8
 */
public interface MessageDelegate {
    //void handleMessage(String message);
    //void handleMessage(Map message);
    //void handleMessage(byte[] message);
    void handleMessage(Serializable message);
    // pass the channel/pattern as well
    //void handleMessage(Serializable message, String channel);
}