package com.david.Config;

import com.david.Component.CustomizeAuthenticationSuccessHandler;
import com.david.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * This class implements security configurations
 *
 * @author David Langen
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Wrapper-Class for the User Repository
     */
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * The successhandler for the Login page
     */
    @Autowired
    private CustomizeAuthenticationSuccessHandler successHandler;

    /**
     * This method configures the permissions for the requests
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2-console").permitAll()
                .antMatchers("/user").hasRole("ADMIN")
                .antMatchers("/*").hasAnyRole("USER", "ADMIN")
                .and()
                .formLogin().successHandler(successHandler).loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout").permitAll()
                .and().httpBasic();
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    /**
     * This method implements global security configuration and
     * connect the user repository with the login system.
     *
     * @param auth The Builder for a AuthenticationManager
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * This Bean encrypt and decrypted passwords for the database
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
