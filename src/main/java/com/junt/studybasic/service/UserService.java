package com.junt.studybasic.service;

import com.junt.studybasic.entity.TbUser;
import com.junt.studybasic.repository.TbUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private TbUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public TbUser registerNewUser(String username, String password, String authority) {
        TbUser user = new TbUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(true);
        user.setAuthority(authority);
        return userRepository.save(user);
    }
}
