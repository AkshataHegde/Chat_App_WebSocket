package com.highpeak.chat.datastore.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "message")
public class MessageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id")
    private Long messageId;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "frn_m_chat_room_id",referencedColumnName = "chat_room_id")
    private ChatRoomModel chatRoomModel;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "frn_m_user_id",referencedColumnName = "user_id")
    private UserModel userModel;

    @Column(name = "message_content")
    private String messageContent;

    @Column(name = "message_created_at")
    private String messageCreatedAt;

    @Column(name = "message_is_deleted")
    private Boolean isDeleted;
}
