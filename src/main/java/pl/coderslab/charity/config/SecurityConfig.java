package pl.coderslab.charity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.coderslab.charity.domain.service.CustomUserDetailService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomUserDetailService userDetailService;

    public SecurityConfig(CustomUserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth
        .userDetailsService(userDetailService)
        .passwordEncoder(passwordEncoder());
    }
/*
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/registration").permitAll()   //puszczać tylko niezalogowanych?
            .antMatchers("/resources/css/**").permitAll()
            .antMatchers("/resources/images/**").permitAll()
            .antMatchers("/resources/js/**").permitAll()
            .antMatchers("/login").permitAll()
            .antMatchers("/").permitAll()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated()
                .and()
            .formLogin()
                .defaultSuccessUrl("/welcome")
                .and()
            .logout().logoutSuccessUrl("/")
                .and()
            .csrf().disable();    // włączyć później
    }
}
