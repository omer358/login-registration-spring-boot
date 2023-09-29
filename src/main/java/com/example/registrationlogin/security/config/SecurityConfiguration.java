package com.example.registrationlogin.security.config;

import com.example.registrationlogin.appuser.AppUser;
import com.example.registrationlogin.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class SecurityConfiguration {

    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http ) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((auth) ->auth
                        .requestMatchers("/api/v*/registration/**","/hello")
                        .permitAll()
                        .anyRequest()
                        .authenticated()

                )
                .formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
