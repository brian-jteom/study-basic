package com.junt.studybasic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;

    //@Lazy 이렇게 하면 JwtRequestFilter 빈의 초기화를 지연시켜 순환 참조 문제를 피할 수 있습니다.
    @Autowired
    public SecurityConfig(@Lazy JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers("/admin/**").hasRole("ADMIN")
//                                .requestMatchers("/user/**").hasRole("USER")
//                                .anyRequest().authenticated()
//                )
//        	•	authorizeHttpRequests 메서드를 사용하여 HTTP 요청에 대한 인가(Authorization) 규칙을 설정합니다.
//	        •	requestMatchers("/admin/**").hasRole("ADMIN"): URL 패턴이 /admin/**인 요청은 ADMIN 역할을 가진 사용자만 접근할 수 있도록 설정합니다.
//          •	requestMatchers("/user/**").hasRole("USER"): URL 패턴이 /user/**인 요청은 USER 역할을 가진 사용자만 접근할 수 있도록 설정합니다.
//          •	anyRequest().authenticated(): 나머지 모든 요청은 인증된 사용자만 접근할 수 있도록 설정합니다.

//        .formLogin(formLogin ->
//                formLogin
//                        .loginPage("/login")
//                        .permitAll()
//        )
//        	•	formLogin 메서드를 사용하여 폼 기반 로그인을 설정합니다.
//          •	loginPage("/login"): 사용자 정의 로그인 페이지를 설정합니다. 사용자가 /login URL로 접근하면 로그인 페이지가 표시됩니다.
//          •	permitAll(): 로그인 페이지는 인증되지 않은 사용자도 접근할 수 있도록 허용합니다.
//
//                .csrf(csrf -> csrf.disable()); // CSRF 보호 비활성화. 필요한 경우 활성화 가능
//        	•	csrf 메서드를 사용하여 CSRF(Cross-Site Request Forgery) 보호 설정을 합니다.
//	        •	csrf.disable(): CSRF 보호를 비활성화합니다. CSRF 보호가 필요할 경우 이 부분을 제거하거나 다른 방법으로 설정할 수 있습니다.
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/auth/**").permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        var userDetailsManager = new InMemoryUserDetailsManager();
//
//        var user = User.withUsername("user")
//                .password(passwordEncoder().encode("password"))
//                .roles("USER")
//                .build();
//
//        var admin = User.withUsername("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//
//        userDetailsManager.createUser(user);
//        userDetailsManager.createUser(admin);
//
//        return userDetailsManager;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * Spring Security 설정 파일에서 AuthenticationManager를 빈으로 정의해야 합니다.
     * Spring Boot 2.x에서는 AuthenticationManager를 직접 정의하는 것이 필요했지만,
     * Spring Boot 3.x에서는 이를 자동으로 구성하지 않으므로 명시적으로 설정해야 합니다.
     * @param authenticationConfiguration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
