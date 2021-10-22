package com.SamuelFumeroHdez.backend.models;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

public class Post {

    @Id
    @SequenceGenerator(name = "POST_GEN", sequenceName = "SEQ_POST", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_GEN")
    private Long postId;

    @NotBlank(message = "Post title is required")
    private String postTitle;

}
