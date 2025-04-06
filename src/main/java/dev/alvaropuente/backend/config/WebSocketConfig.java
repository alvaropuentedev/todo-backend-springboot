package dev.alvaropuente.backend.config;

import dev.alvaropuente.backend.handler.ItemWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final ItemWebSocketHandler itemWebSocketHandler;

    public WebSocketConfig(ItemWebSocketHandler itemWebSocketHandler) {
        this.itemWebSocketHandler = itemWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(itemWebSocketHandler, "/apitodo/ws/items").setAllowedOrigins("*");
    }
}