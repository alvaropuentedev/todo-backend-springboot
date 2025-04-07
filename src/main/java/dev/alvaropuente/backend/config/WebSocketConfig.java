package dev.alvaropuente.backend.config;

import dev.alvaropuente.backend.handler.ItemWebSocketHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Value("${WEBSOCKET_ENDPOINT}")
    private String[] WEBSOCKET_ENDPOINT;

    private final ItemWebSocketHandler itemWebSocketHandler;

    public WebSocketConfig(ItemWebSocketHandler itemWebSocketHandler) {
        this.itemWebSocketHandler = itemWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(itemWebSocketHandler, WEBSOCKET_ENDPOINT).setAllowedOrigins("*");
    }
}