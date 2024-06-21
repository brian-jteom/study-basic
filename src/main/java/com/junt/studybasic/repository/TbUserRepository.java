package com.junt.studybasic.repository;

import com.junt.studybasic.entity.TbUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbUserRepository extends JpaRepository<TbUser, Long> {
    TbUser findByUsername(String username);
}
