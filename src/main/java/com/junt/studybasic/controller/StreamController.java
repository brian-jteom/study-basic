package com.junt.studybasic.controller;

import com.junt.studybasic.dto.BlogReq;
import com.junt.studybasic.service.BlogService;
import com.junt.studybasic.service.StreamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@CrossOrigin(origins="*")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/stream")
public class StreamController {

    private final StreamService streamService;

    @GetMapping(value = "/list-all")
    public CompletableFuture<ResponseEntity<Object>> getBlogList() {
        return streamService.getListAll()
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping(value = "/detail/{blogId}")
    public CompletableFuture<ResponseEntity<Object>> getBlogDetail(
            @PathVariable Long blogId
    ) {
        return streamService.getBlogByIdV1(blogId)
                .thenApply(ResponseEntity::ok);
    }

    @PostMapping(value = "/create")
    public CompletableFuture<ResponseEntity<Object>> postBlog(@RequestBody BlogReq req) {
        return streamService.createBlog(req)
                .thenApply(ResponseEntity::ok);
    }

    @PutMapping(value = "/update/{blogId}")
    public CompletableFuture<ResponseEntity<Object>> putBlog(@RequestBody BlogReq req) {
        return streamService.updateBlog(req)
                .thenApply(ResponseEntity::ok);
    }
}
