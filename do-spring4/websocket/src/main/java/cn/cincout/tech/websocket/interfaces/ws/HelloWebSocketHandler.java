package cn.cincout.tech.websocket.interfaces.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhaoyu on 17-8-25.
 *
 * @author zhaoyu
 * @sine 1.8
 */
public class HelloWebSocketHandler extends AbstractWebSocketHandler {
    private final static Logger LOG = LoggerFactory.getLogger(HelloWebSocketHandler.class);

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //super.handleTextMessage(session, message);
        LOG.info("received message : {}", message.getPayload());
        TimeUnit.SECONDS.sleep(1);
        session.sendMessage(new TextMessage("hello world! " + new Date()));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //super.afterConnectionEstablished(session);
        LOG.info("session id is {}", session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //super.afterConnectionClosed(session, status);
        LOG.info("session id is {}", session.getId());
    }
}
