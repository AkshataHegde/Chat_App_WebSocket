package com.highpeak.chat.service.Impl;


import com.highpeak.chat.Bean.ChatRoomBean;
import com.highpeak.chat.Bean.UserBean;
import com.highpeak.chat.datastore.Repository.ChatRoomModelRepository;
import com.highpeak.chat.datastore.Repository.ChatRoomToUserModelRepository;
import com.highpeak.chat.datastore.Repository.UserModelRepository;
import com.highpeak.chat.exception.DataException;
import com.highpeak.chat.datastore.model.ChatRoomModel;
import com.highpeak.chat.datastore.model.ChatRoomToUserModel;
import com.highpeak.chat.datastore.model.UserModel;
import com.highpeak.chat.service.UserService;
import com.highpeak.chat.util.DateUtil;
import com.highpeak.chat.util.NullEmptyUtils;
import com.highpeak.chat.util.StringConstantUtil;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.highpeak.chat.util.StringConstantUtil.*;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);


    @Autowired
    private UserModelRepository userModelRepository;

   /* @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;*/

    @Autowired
    private ChatRoomModelRepository chatRoomModelRepository;

    @Autowired
    private ChatRoomToUserModelRepository chatRoomToUserModelRepository;

    @Override
    public String registerUser(UserBean userBean) throws DataException {
        try {
            if (NullEmptyUtils.isNull(userBean)) {
                throw new DataException(StringConstantUtil.CHAT_ERROR, StringConstantUtil.INPUT_SHOULD_NOT_BE_NULL, HttpStatus.NOT_FOUND);
            }

            UserModel userModel = new UserModel();
            userModel.setUserName(userBean.getUserName());
            userModel.setUserEmail(userBean.getEmailId());

//          BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
            userModel.setPassword(userBean.getPassword());
            userModel.setIsSessionActive(true);
            userModel.setCreatedAt(DateUtil.getUTCCalenderInstance(System.currentTimeMillis()));
            userModel.setIsActive(true);
            userModel.setIsDeleted(false);

            userModelRepository.save(userModel);

            return YOU_HAVE_SUCCESSFULLY_REGISTERED_AND_LOGGED_IN;
        } catch (DataException e) {
            LOGGER.error(ERROR, e);
            throw e;

        } catch (Exception e) {
            LOGGER.error(e);
            throw new DataException(StringConstantUtil.CHAT_ERROR, REGISTRATION_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @Override
    public String createChatRoom(ChatRoomBean chatRoomBean) throws DataException {
        try {
            if (NullEmptyUtils.isNull(chatRoomBean)) {
                throw new DataException(StringConstantUtil.CHAT_ERROR, StringConstantUtil.INPUT_SHOULD_NOT_BE_NULL, HttpStatus.NOT_FOUND);
            }

            ChatRoomModel chatRoomModel = new ChatRoomModel();
            chatRoomModel.setChatRoomName(chatRoomBean.getChatRoomName());
            chatRoomModel.setCreatedAt(DateUtil.getUTCCalenderInstance(System.currentTimeMillis()));
            chatRoomModel.setIsActive(true);
            chatRoomModel.setIsDeleted(false);

            ChatRoomModel chatRoom = chatRoomModelRepository.save(chatRoomModel);

            for (Long participantId : chatRoomBean.getParticipantIdList()) {
                ChatRoomToUserModel chatRoomToUserModel = new ChatRoomToUserModel();
                chatRoomToUserModel.setCuChatRoomId(chatRoom.getChatRoomId());
                chatRoomToUserModel.setChatRoomToUserId(participantId);
                chatRoomToUserModelRepository.save(chatRoomToUserModel);
            }
            return GROUP_HAS_BEEN_CREATED_SUCCESSFULLY;
        } catch (DataException e) {
            LOGGER.error(ERROR, e);
            throw e;

        } catch (Exception e) {
            LOGGER.error(e);
            throw new DataException(StringConstantUtil.CHAT_ERROR, SOMETHING_WENT_WRONG_WHILE_CREATING_CHAT_ROOM, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
