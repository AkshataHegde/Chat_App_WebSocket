package com.highpeak.chat.datastore.Repository;

import com.highpeak.chat.datastore.model.ChatRoomModel;
import com.highpeak.chat.datastore.model.MessageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MessageModelRepository extends JpaRepository<MessageModel,Long> {

    List<MessageModel> findByChatRoomModelAndIsDeletedFalse(ChatRoomModel chatRoomModel);
}
