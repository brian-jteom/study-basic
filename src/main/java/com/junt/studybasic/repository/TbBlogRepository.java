package com.junt.studybasic.repository;

import com.junt.studybasic.entity.TbBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbBlogRepository extends JpaRepository<TbBlog, Long> {


}
