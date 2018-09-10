package com.highpeak.chat.controller;

import com.highpeak.chat.Bean.ChatRoomBean;
import com.highpeak.chat.Bean.UserBean;
import com.highpeak.chat.exception.DataException;
import com.highpeak.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/chat")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public String registerUser(@RequestBody UserBean userBean) throws DataException {
        try
        {
           return userService.registerUser(userBean);
        }
        catch(DataException e)
        {
            throw e;
        }
    }

    @PostMapping("/group")
    public String createGroup(@RequestBody ChatRoomBean chatRoomBean) throws DataException {
        try
        {
            return userService.createChatRoom(chatRoomBean);
        }
        catch (DataException e)
        {
            throw e;
        }
    }

    @PostMapping("/subscription/{chatRoomId}")
    public String addToChatRoom(@RequestBody List<Long> userIdList,@PathVariable("chatRoomId") Long chatRoomId) throws DataException {
        try
        {
            return userService.addToChatRoom(chatRoomId,userIdList);
        }
        catch (DataException e)
        {
            throw e;
        }
    }

    @PostMapping("/unSubscription/{chatRoomId}/{userId}")
    public String leaveChatRoom(@PathVariable("userId") Long userId,@PathVariable("chatRoomId") Long chatRoomId) throws DataException {
        try
        {
            return userService.leaveChatRoom(userId,chatRoomId);
        }
        catch (DataException e)
        {
            throw e;
        }
    }

}
