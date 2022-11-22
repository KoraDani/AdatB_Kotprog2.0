package com.okosotthon.config;

import com.okosotthon.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .antMatchers("/szobak/monitor").hasAnyAuthority("ROLE_USER")
                        .anyRequest().permitAll()
                );
//        http.authorizeRequests()
//                .antMatchers("/meresek/**").authenticated()
//                .antMatchers("/szobak/monitor").authenticated()
//                .antMatchers("/users/**").authenticated()
//                .antMatchers("/regiszt").permitAll()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/").permitAll()
//                .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                    .loginPage("/users/login").permitAll()
//                .and()
//                    .logout()
//                    .logoutSuccessUrl("/login?logout").permitAll();
    }

}
