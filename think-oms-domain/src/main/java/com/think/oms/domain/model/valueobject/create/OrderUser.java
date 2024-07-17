package com.think.oms.domain.model.valueobject.create;

import lombok.Getter;

@Getter
public class OrderUser {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户类型
     */
    private UserType userType;

    public OrderUser(Long userId, String userName, UserType userType){

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
