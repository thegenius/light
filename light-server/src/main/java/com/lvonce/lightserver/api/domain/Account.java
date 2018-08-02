package com.lvonce.lightserver.api.domain;

import lombok.Data;


@Data
public class Account {
    private String account;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
}
