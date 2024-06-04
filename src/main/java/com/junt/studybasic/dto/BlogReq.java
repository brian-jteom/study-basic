package com.junt.studybasic.dto;


import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BlogReq {

    private Long blogId;
    private String blogTitle;
    private String blogContent;
}
