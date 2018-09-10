package com.highpeak.chat.datastore.Repository;

import com.highpeak.chat.datastore.model.ChatRoomModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomModelRepository extends JpaRepository<ChatRoomModel,Long> {

    Optional<ChatRoomModel> findByChatRoomIdAndIsActiveTrueAndIsDeletedFalse(Long chatRoomId);

}
