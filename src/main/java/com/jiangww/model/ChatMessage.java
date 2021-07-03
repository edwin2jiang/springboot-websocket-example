package com.jiangww.model;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Z09418208_蒋伟伟
 * @Description
 * @create 2021-07-03 17:06
 */
@Builder
public class ChatMessage {
    @Getter
    private MessageType type;
    @Getter
    private String content;
    @Getter
    private String sender;
    @Getter
    private String time;

    public ChatMessage() {
    }

    public ChatMessage(MessageType type, String content, String sender, String time) {
        this.type = type;
        this.content = content;
        this.sender = sender;
        this.time = time;
    }
}
