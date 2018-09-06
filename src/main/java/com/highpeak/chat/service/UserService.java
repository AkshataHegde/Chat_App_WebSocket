package com.highpeak.chat.service;


import com.highpeak.chat.Bean.ChatRoomBean;
import com.highpeak.chat.Bean.UserBean;
import com.highpeak.chat.exception.DataException;
import org.springframework.stereotype.Service;


public interface UserService {

    String registerUser(UserBean userBean) throws DataException;

    String createChatRoom(ChatRoomBean chatRoomBean) throws DataException;

}
