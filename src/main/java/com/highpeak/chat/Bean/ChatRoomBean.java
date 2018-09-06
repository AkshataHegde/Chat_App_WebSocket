package com.highpeak.chat.Bean;

import lombok.Data;

import java.util.List;

@Data
public class ChatRoomBean {

    private String chatRoomName;

    private List<Long> participantIdList;
}
