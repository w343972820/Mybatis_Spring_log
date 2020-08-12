package com.vico.model;

import lombok.Data;

@Data
public class UserSms {
    private int id;
    private String phoneormail;
    private String code;
    private String createtime;
}
