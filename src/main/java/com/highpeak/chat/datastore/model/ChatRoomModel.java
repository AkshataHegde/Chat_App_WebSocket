package com.highpeak.chat.datastore.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;

@Data
@Entity
@Table(name = "chat_room")
public class ChatRoomModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "chat_room_id")
    private Long chatRoomId;

    @Column(name = "chat_room_name")
    private String chatRoomName;

    @Column(name = "chat_room_created_at")
    private Calendar createdAt;

    /*@ManyToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "frn_cr_user_id", referencedColumnName = "user_id")
    private List<UserModel> userModelList;*/

    @Column(name = "chat_room_is_active")
    private Boolean isActive;

    @Column(name = "chat_room_is_deleted")
    private Boolean isDeleted;

}
