package com.highpeak.chat.datastore.Repository;

import com.highpeak.chat.datastore.model.MessageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageModelRepository extends JpaRepository<MessageModel,Long> {
}
