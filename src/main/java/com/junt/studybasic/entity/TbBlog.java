package com.junt.studybasic.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name="tb_blog")
public class TbBlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blogId;

    @Column(nullable = false, length = 100)
    private String blogTitle;

    @Column(name = "blog_content", nullable = false, length = 100)
    private String blogContent;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime regDt;

    @UpdateTimestamp
    @Column
    private LocalDateTime updDt;
}
