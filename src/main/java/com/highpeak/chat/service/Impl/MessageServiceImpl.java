package com.highpeak.chat.service.Impl;

import com.highpeak.chat.Bean.ChatRoomMessageBean;
import com.highpeak.chat.Bean.MessageBean;
import com.highpeak.chat.datastore.Repository.ChatRoomModelRepository;
import com.highpeak.chat.datastore.Repository.MessageModelRepository;
import com.highpeak.chat.datastore.Repository.UserModelRepository;
import com.highpeak.chat.datastore.model.ChatRoomModel;
import com.highpeak.chat.datastore.model.MessageModel;
import com.highpeak.chat.datastore.model.UserModel;
import com.highpeak.chat.exception.DataException;
import com.highpeak.chat.service.MessageService;
import com.highpeak.chat.util.DateUtil;
import com.highpeak.chat.util.NullEmptyUtils;
import com.highpeak.chat.util.StringConstantUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.highpeak.chat.util.StringConstantUtil.*;

@Service
public class MessageServiceImpl implements MessageService {

    private static final Logger LOGGER=Logger.getLogger(MessageServiceImpl.class);

    @Autowired
    private ChatRoomModelRepository chatRoomModelRepository;

    @Autowired
    private UserModelRepository userModelRepository;

    @Autowired
    private MessageModelRepository messageModelRepository;


    @Override
    public String saveMessages(MessageBean messageBean) throws DataException {
        try
        {
            if(NullEmptyUtils.isNull(messageBean))
            {
                throw new DataException(StringConstantUtil.CHAT_ERROR,INPUT_SHOULD_NOT_BE_NULL,HttpStatus.NOT_FOUND);
            }

            MessageModel messageModel=new MessageModel();

            //fetching chat room details
            Optional<ChatRoomModel> chatRoomModelOptional=chatRoomModelRepository.findByChatRoomIdAndIsActiveTrueAndIsDeletedFalse(messageBean.getChatRoomId());
            if(!chatRoomModelOptional.isPresent())
            {
                throw new DataException(StringConstantUtil.CHAT_ERROR,INVALID_CHAT_ROOM_ID,HttpStatus.NOT_FOUND);
            }
            messageModel.setChatRoomModel(chatRoomModelOptional.get());

            //fetching user details
            Optional<UserModel> userModelOptional=userModelRepository.findByUserIdAndIsActiveTrueAndIsDeletedFalse(messageBean.getSenderId());
            if(!userModelOptional.isPresent())
            {
                throw new DataException(StringConstantUtil.CHAT_ERROR,INVALID_USER_ID,HttpStatus.NOT_FOUND);
            }

            messageModel.setUserModel(userModelOptional.get());

            messageModel.setMessageContent(messageBean.getContent());
            messageModel.setMessageCreatedAt(DateUtil.getUTCCalenderInstance(System.currentTimeMillis()));
            messageModel.setMessageType(messageBean.getType());
            messageModel.setIsDeleted(false);

            messageModelRepository.save(messageModel);

            return MESSAGE_HAS_BEEN_SAVED_SUCCESSFULLY;
        }
        catch (DataException e)
        {
            LOGGER.error(e);
            throw e;
        }
        catch(Exception e)
        {
            LOGGER.error(ERROR,e);
            throw new DataException(StringConstantUtil.CHAT_ERROR,SOMETHING_WENT_WRONG_WHILE_SAVING_MESSAGES, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    @Override
    public List<ChatRoomMessageBean> getChatRoomMessages(Long chatRoomId) throws DataException {
        try
        {
            if(NullEmptyUtils.isNullorEmpty(chatRoomId))
            {
               throw new DataException(StringConstantUtil.CHAT_ERROR,GROUP_ID_SHOULD_NOT_BE_NULL_OR_EMPTY,HttpStatus.NOT_FOUND);
            }

            Optional<ChatRoomModel> chatRoomModelOptional=chatRoomModelRepository.findByChatRoomIdAndIsActiveTrueAndIsDeletedFalse(chatRoomId);

            if(!chatRoomModelOptional.isPresent())
            {
               throw new DataException(StringConstantUtil.CHAT_ERROR,INVALID_CHAT_ROOM_ID,HttpStatus.NOT_FOUND);
            }

            ChatRoomModel chatRoomModel=chatRoomModelOptional.get();

            List<MessageModel> messageModelList= messageModelRepository.findByChatRoomModelAndIsDeletedFalse(chatRoomModel);

            List<ChatRoomMessageBean> chatRoomMessageBeanList=new ArrayList<>();
            for(MessageModel messageModel:messageModelList)
            {
                ChatRoomMessageBean chatRoomMessageBean=new ChatRoomMessageBean();
                chatRoomMessageBean.setMessageId(messageModel.getMessageId());
                chatRoomMessageBean.setMessageContent(messageModel.getMessageContent());
                chatRoomMessageBean.setSenderId(messageModel.getUserModel().getUserId());
                chatRoomMessageBean.setSentDate(messageModel.getMessageCreatedAt().getTimeInMillis());

                chatRoomMessageBeanList.add(chatRoomMessageBean);
            }
            return chatRoomMessageBeanList;
        }
        catch(DataException e)
        {
            LOGGER.error(ERROR,e);
            throw e;
        }
        catch(Exception e)
        {
            LOGGER.error(ERROR,e);
            throw new DataException(StringConstantUtil.CHAT_ERROR,SOMETHING_WENT_WRONG_WHILE_FETCHING_MESSAGES,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
