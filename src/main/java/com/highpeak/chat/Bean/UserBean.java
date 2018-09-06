package com.highpeak.chat.Bean;

import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
public class UserBean {

    private String userName;

    private String emailId;

    private String password;

    private String confirmPassword;

}
