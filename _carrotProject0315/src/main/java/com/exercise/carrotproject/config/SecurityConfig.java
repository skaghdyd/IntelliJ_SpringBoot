//package com.exercise.carrotproject.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    public void configure(WebSecurity web) throws Exception {
////        super.configure(web);
//        web.ignoring().antMatchers("/h2-console/**");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        super.configure(http);
//        http
//                .authorizeRequests()
//                .antMatchers("/","/**").access("permitAll")
//                .antMatchers("/h2-console/**").permitAll() // 추가
//                .and()
//                .csrf() // 추가
//                .ignoringAntMatchers("/h2-console/**").disable() // 추가
//                .httpBasic();
//    }
//}
