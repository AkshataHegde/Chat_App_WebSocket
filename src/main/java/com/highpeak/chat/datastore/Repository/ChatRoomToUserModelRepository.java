package com.highpeak.chat.datastore.Repository;

import com.highpeak.chat.datastore.model.ChatRoomToUserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomToUserModelRepository extends JpaRepository<ChatRoomToUserModel,Long> {


    @Modifying
    @Query(value = "UPDATE ChatRoomToUserModel c SET c.isDeleted=true where c.cuChatRoomId= :chatRoomId AND c.cuUserId= :userId ")
    Integer leaveGroup(@Param("chatRoomId") Long chatRoomId,@Param("userId") Long userId);
}
