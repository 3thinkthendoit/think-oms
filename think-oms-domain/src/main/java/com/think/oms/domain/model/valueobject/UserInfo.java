package com.think.oms.domain.model.valueobject;

import lombok.Getter;

@Getter
public class UserInfo {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户类型
     */
    private UserType userType;

    public UserInfo(Long userId, String username, UserType userType){
        this.userId =  userId;
        this.username = username;
        this.userType = userType;
    }


    public static enum UserType{
        TO_B(1,"TO_B"),TO_C(2,"TO_C");

        private int code;
        private String desc;

        private UserType(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }
}
