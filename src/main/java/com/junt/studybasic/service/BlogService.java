package com.junt.studybasic.service;

import com.junt.studybasic.dto.BlogReq;
import com.junt.studybasic.entity.TbBlog;
import com.junt.studybasic.entity.TbBlog;
import com.junt.studybasic.exception.BlogNotFoundException;
import com.junt.studybasic.repository.TbBlogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogService {


    private final TbBlogRepository tbBlogRepository;

    public TbBlog createBlog(BlogReq req) {
        TbBlog tbBlog = new TbBlog();
        tbBlog.setBlogTitle(req.getBlogTitle());
        tbBlog.setBlogContent(req.getBlogContent());

        return tbBlogRepository.save(tbBlog); // 생성된 로또 번호 Set 반환
    }


    public TbBlog updateBlog(BlogReq req) {
        TbBlog tbBlog = new TbBlog();
        tbBlog.setBlogId(req.getBlogId());
        tbBlog.setBlogTitle(req.getBlogTitle());
        tbBlog.setBlogContent(req.getBlogContent());

        return tbBlogRepository.save(tbBlog); // 생성된 로또 번호 Set 반환
    }


    public List<TbBlog> getListAll() {

        return tbBlogRepository.findAll();
    }

    public TbBlog getBlogId(Long blogId) {
        Optional<TbBlog> blog = tbBlogRepository.findById(blogId);
        return blog.get(); // 생성된 로또 번호 Set 반환
    }

    /**
     * Optional.isPresent() 메서드를 사용하여 값이 존재하는지 확인한 후에 값을 처리하는 방법입니다.
     */
    public TbBlog getBlogByIdV1(Long blogId) {
        Optional<TbBlog> blog = tbBlogRepository.findById(blogId);
        if (blog.isPresent()) {
            return blog.get();
        } else {
            // 값이 없을 경우 예외를 던지거나 다른 처리를 합니다.
            throw new BlogNotFoundException("Blog not found with id: " + blogId);
        }
    }

    /**
     * orElse() 메서드 사용
     * Optional에 값이 없을 경우 기본 값을 반환하는 방법입니다.
     */
    public TbBlog getBlogByIdV2(Long blogId) {
        Optional<TbBlog> blog = tbBlogRepository.findById(blogId);
        return blog.orElse(null); // 또는 적절한 기본값을 설정할 수 있습니다.
    }

    /**
     * orElseThrow() 메서드 사용
     * Optional에 값이 없을 경우 예외를 던지는 방법입니다.
     */
    public TbBlog getBlogByIdV3(Long blogId) {
        return tbBlogRepository.findById(blogId)
                .orElseThrow(() -> new BlogNotFoundException("Blog not found with id: " + blogId));
    }

}
