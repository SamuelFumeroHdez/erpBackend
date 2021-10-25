package com.erp.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEmail {

    @Column(nullable = false)
    private String subject;
    private String recepient;
    private String body;

}
