package com.junt.studybasic.controller;

import com.junt.studybasic.config.JwtUtil;
import com.junt.studybasic.dto.AuthRequest;
import com.junt.studybasic.dto.RegisterRequest;
import com.junt.studybasic.entity.TbUser;
import com.junt.studybasic.service.CustomUserDetailsService;
import com.junt.studybasic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return jwt;
    }

    @PostMapping("/register")
    public TbUser registerUser(@RequestBody RegisterRequest registerRequest) {
        return userService.registerNewUser(
                registerRequest.getUsername(),
                registerRequest.getPassword(),
                registerRequest.getAuthority()
        );
    }
}

