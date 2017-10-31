package cn.cincout.tech.websocket.interfaces.ws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Created by zhaoyu on 17-8-25.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@EnableWebSocket
@Configuration
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(helloWebSocketHandler(), "ws/hello").setAllowedOrigins("*");
    }

    @Bean
    public HelloWebSocketHandler helloWebSocketHandler() {
        return new HelloWebSocketHandler();
    }
}
