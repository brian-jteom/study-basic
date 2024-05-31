package com.junt.studybasic.controller;

import com.junt.studybasic.dto.BlogReq;
import com.junt.studybasic.service.BlogService;
import com.junt.studybasic.service.RandomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;


    @GetMapping(value = "/list-all")
    public ResponseEntity<Object> getBlogList() {
        return ResponseEntity.ok(blogService.getListAll());
    }

    @GetMapping(value = "/detail/{blogId}")
    public ResponseEntity<Object> getBlogDetail(
            @PathVariable Long blogId
    ) {
        return ResponseEntity.ok(blogService.getBlogByIdV1(blogId));
    }


    @PostMapping(value = "/create")
    public ResponseEntity<Object> postBlog(BlogReq req) {
        return ResponseEntity.ok(blogService.createBlog(req));
    }

    @PutMapping(value = "/update/{blogId}")
    public ResponseEntity<Object> putBlog(
                BlogReq req) {
        return ResponseEntity.ok(blogService.updateBlog(req));
    }
}
