package com.highpeak.chat.service;

import com.highpeak.chat.Bean.ChatRoomMessageBean;
import com.highpeak.chat.Bean.MessageBean;
import com.highpeak.chat.exception.DataException;

import java.util.List;

public interface MessageService {

    String saveMessages(MessageBean messageBean) throws DataException;

    List<ChatRoomMessageBean> getChatRoomMessages(Long chatRoomId) throws DataException;
}
