package com.project.springreact.security;

import com.project.springreact.service.CustomOAuth2UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequestEntityConverter;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
@Configuration
@Slf4j
public class WebSecurityConfig implements WebMvcConfigurer {
    @Autowired
    private JwtAuthicationFilter jwtAuthicationFilter;
    @Autowired
    private CustomOAuth2UserService oauth2UserService;
    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oath2UserService(){
        DefaultOAuth2UserService userService = new DefaultOAuth2UserService();
        userService.setRequestEntityConverter(new OAuth2UserRequestEntityConverter());
        return userService;
    }
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http)throws Exception{
        http.cors() // WebMvCConfig 에서 설정이 이루어졌으므로.
                .and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/","/auth/**","/oauth2/**").permitAll()
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
//        http.oauth2Login(oauth2 -> oauth2
//                .loginPage("/oauth2/google")
//                .userInfoEndpoint(userinfo -> userinfo
//                        .userService(oauth2UserService)
//                )
//        );
        return http.build();
    }
}
