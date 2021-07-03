package com.jiangww.controller;

import com.jiangww.model.ChatMessage;
import com.jiangww.model.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;


/**
 * @author Z09418208_蒋伟伟
 * @Description
 * @create 2021-07-03 17:23
 */
@Component
public class WebSocketEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations sendingOperations;

    @EventListener
    public void handlerWebSocketConnectListener(final SessionConnectEvent connectEvent){
        LOGGER.info("bing bing bing! We have a new connect");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(final SessionConnectEvent connectEvent){
        final StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(connectEvent.getMessage());

        final String username = (String) headerAccessor.getSessionAttributes().get("username");

        final ChatMessage chatMessage = ChatMessage.builder()
                .type(MessageType.DISCONNECT)
                .sender(username)
                .build();

        sendingOperations.convertAndSend("/topic/public",chatMessage);

    }
}
