package com.highpeak.chat.Bean;

import lombok.Data;

@Data
public class MessageBean {

    private ChatMessage.MessageType type;
    private String content;
    private String senderName;
    private Long senderId;
    private String emailId;
    private String password;
    private Long chatRoomId;

}
