package com.SamuelFumeroHdez.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        schema = "erp",
        name = "users"
)
@Entity
public class User {
    @Id
    @SequenceGenerator(name = "USER_GEN", sequenceName = "SEQ_USER", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_GEN")
    private Long userId;

    @NotBlank(message = "Username is required")
    @Column(name = "username", nullable = false)
    private String userName;

    @NotBlank(message = "Password is required")
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank(message = "Name is required")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Lastname is required")
    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Email
    @NotBlank(message = "Email is required")
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "creationdate", nullable = false)
    private Instant creationDate;

    @Column(name = "accountstatus", nullable = false)
    private boolean accountStatus;


}
