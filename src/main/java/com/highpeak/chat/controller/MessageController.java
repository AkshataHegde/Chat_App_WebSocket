package com.highpeak.chat.controller;


import com.highpeak.chat.Bean.ChatRoomMessageBean;
import com.highpeak.chat.Bean.MessageBean;
import com.highpeak.chat.exception.DataException;
import com.highpeak.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/msg")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/message")
    public String saveMessage(@RequestBody MessageBean messageBean) throws DataException {
        try {
            return messageService.saveMessages(messageBean);
        } catch (DataException e) {
            throw e;
        }
    }

    @GetMapping("/message/{chatRoomId}")
    public List<ChatRoomMessageBean> getMessage(@PathVariable("chatRoomId") Long chatRoomId) throws DataException {
        try {
            return messageService.getChatRoomMessages(chatRoomId);
        } catch (DataException e) {
            throw e;
        }
    }

}
