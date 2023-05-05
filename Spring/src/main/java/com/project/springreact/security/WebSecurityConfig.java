package com.project.springreact.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity(debug = true)
@Configuration
@Slf4j
public class WebSecurityConfig implements WebMvcConfigurer {
    @Autowired
    private JwtAuthicationFilter jwtAuthicationFilter;
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http)throws Exception{
        http.cors() // WebMvCConfig 에서 설정이 이루어졌으므로.
                .and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/","/auth/**").permitAll()
                        .shouldFilterAllDispatcherTypes(false)
                        .anyRequest()
                        .authenticated()
                );
        //filter 등록.
        //매 요청마다 Corsfilter 실행한 후에
        //jwtAuthenticationFilter 실행한다.
        http.addFilterAfter(
                jwtAuthicationFilter,
                CorsFilter.class
        );
        return http.build();
    }
}
