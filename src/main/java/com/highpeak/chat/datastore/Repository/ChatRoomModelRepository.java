package com.highpeak.chat.datastore.Repository;

import com.highpeak.chat.datastore.model.ChatRoomModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomModelRepository extends JpaRepository<ChatRoomModel,Long> {
}
