package com.junt.studybasic.service;

import com.junt.studybasic.dto.BlogReq;
import com.junt.studybasic.entity.TbBlog;
import com.junt.studybasic.exception.BlogNotFoundException;
import com.junt.studybasic.repository.TbBlogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StreamService {


    private final TbBlogRepository tbBlogRepository;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10); // 스레드 풀 생성

    public CompletableFuture<TbBlog> createBlog(BlogReq req) {
        return CompletableFuture.supplyAsync(() -> {
            TbBlog tbBlog = new TbBlog();
            tbBlog.setBlogTitle(req.getBlogTitle());
            tbBlog.setBlogContent(req.getBlogContent());
            return tbBlogRepository.save(tbBlog);
        }, executorService);
    }

    public CompletableFuture<TbBlog> updateBlog(BlogReq req) {
        return CompletableFuture.supplyAsync(() -> {
            TbBlog tbBlog = new TbBlog();
            tbBlog.setBlogId(req.getBlogId());
            tbBlog.setBlogTitle(req.getBlogTitle());
            tbBlog.setBlogContent(req.getBlogContent());
            return tbBlogRepository.save(tbBlog);
        }, executorService);
    }

    public CompletableFuture<List<TbBlog>> getListAll() {


        return CompletableFuture.supplyAsync(() -> new ArrayList<>(tbBlogRepository.findAll()), executorService);
    }

    public CompletableFuture<TbBlog> getBlogId(Long blogId) {
        return CompletableFuture.supplyAsync(() -> {
            Optional<TbBlog> blog = tbBlogRepository.findById(blogId);
            return blog.orElseThrow(() -> new BlogNotFoundException("Blog not found with id: " + blogId));
        }, executorService);
    }

    public CompletableFuture<TbBlog> getBlogByIdV1(Long blogId) {
        return CompletableFuture.supplyAsync(() -> {
            Optional<TbBlog> blog = tbBlogRepository.findById(blogId);
            if (blog.isPresent()) {
                return blog.get();
            } else {
                throw new BlogNotFoundException("Blog not found with id: " + blogId);
            }
        }, executorService);
    }

    public CompletableFuture<TbBlog> getBlogByIdV2(Long blogId) {
        return CompletableFuture.supplyAsync(() -> {
            Optional<TbBlog> blog = tbBlogRepository.findById(blogId);
            return blog.orElse(null);
        }, executorService);
    }

    public CompletableFuture<TbBlog> getBlogByIdV3(Long blogId) {
        return CompletableFuture.supplyAsync(() -> tbBlogRepository.findById(blogId)
                .orElseThrow(() -> new BlogNotFoundException("Blog not found with id: " + blogId)), executorService);
    }

}
