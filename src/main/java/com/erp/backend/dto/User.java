package com.erp.backend.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class User {

    private Long userId;
    private String userName;
    private String password;
    private String email;
    private Instant creationDate;
    private boolean accountStatus;

}
