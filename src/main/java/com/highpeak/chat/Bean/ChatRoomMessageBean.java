package com.highpeak.chat.Bean;


import lombok.Data;

@Data
public class ChatRoomMessageBean {

    private Long messageId;

    private String messageContent;

    private Long senderId;

    private Long sentDate;
}
