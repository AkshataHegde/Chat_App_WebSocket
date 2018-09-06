package com.highpeak.chat.datastore.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Data
@Table(name = "user")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_createdAt")
    private Calendar CreatedAt;

    @Column(name = "user_password")
    private String password;

  /*  @ManyToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "frn_u_chat_room_id", referencedColumnName = "chat_room_id")
    private List<ChatRoomModel> chatRoomModelList;*/

    @Column(name = "user_is_session_active")
    private Boolean isSessionActive;

    @Column(name = "user_is_active")
    private Boolean isActive;

    @Column(name = "user_is_deleted")
    private Boolean isDeleted;


}
