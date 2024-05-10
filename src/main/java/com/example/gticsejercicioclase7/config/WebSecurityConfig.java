package com.example.gticsejercicioclase7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig {


    final DataSource dataSource;
    public WebSecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsManager users(DataSource dataSource){
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        String sql1 = "SELECT email, password FROM users WHERE email = ?";
        String sql2 = "SELECT u.email, r.name FROM users u "
                + "INNER JOIN roles r ON (u.idrol = r.id) "
                + "WHERE u.email = ?";

        users.setUsersByUsernameQuery(sql1);
        users.setAuthoritiesByUsernameQuery(sql2);
        return users;
    }



    /*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.formLogin()
           .loginPage("/loginForm")
           .loginProcessingUrl("/processLogin");

        http.authorizeHttpRequests()
           .requestMatchers("/employee", "/employee/**").hasAnyAuthority("admin", "logistica")
           .requestMatchers("/shipper", "/shipper/**").hasAuthority("admin")
           .anyRequest().permitAll();

        return http.build();
    }*/

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.formLogin(formLogin ->
                formLogin
                        .loginPage("/loginForm")
                        .loginProcessingUrl("/submitLoginForm")
                        //.permitAll()
        );


        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                                .requestMatchers("/personaje/list").hasAnyRole("USER", "EDITOR", "ADMIN")
                                .requestMatchers("/personaje/new").hasAnyRole("EDITOR", "ADMIN")
                                .requestMatchers("/personaje/edit").hasAnyRole("EDITOR", "ADMIN")
                                .requestMatchers("/personaje/delete").hasAnyRole("ADMIN")
                                .anyRequest().permitAll());
                //.httpBasic(Customizer.withDefaults())
                //.sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

}
