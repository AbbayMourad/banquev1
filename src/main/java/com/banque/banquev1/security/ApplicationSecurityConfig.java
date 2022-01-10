package com.banque.banquev1.security;

import com.banque.banquev1.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;


    @Autowired
    private DataSource dataSource;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("SELECT username,password,enabled FROM users WHERE username=?")
//                .authoritiesByUsernameQuery("SELECT username,authority FROM authorities WHERE username=?")
//                .passwordEncoder(passwordEncoder);
//    }


    @Bean
    public UserDetailsServiceImpl userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(this.passwordEncoder);

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                .authorizeRequests()
                .antMatchers("/home","/css/*","/js/*").permitAll()
                .antMatchers("/admin").hasRole(AppUserRole.ADMIN.name())
                .antMatchers("/user").hasRole(AppUserRole.USER.name())
//                .antMatchers(HttpMethod.POST,"/api/v2/**").hasAuthority(AppUserPermission.USER_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT,"/api/v2/**").hasAuthority(AppUserPermission.USER_WRITE.getPermission())
//                .antMatchers(HttpMethod.DELETE,"/api/v2/**").hasAuthority(AppUserPermission.USER_WRITE.getPermission())
//                .antMatchers(HttpMethod.GET,"/api/v2/**").hasAnyRole(AppUserRole.USER.name())
                .anyRequest()
                .authenticated()
                .and().formLogin().loginPage("/login").permitAll()
                .and().logout().permitAll().logoutUrl("/logout").logoutSuccessUrl("/home")
                .and().exceptionHandling().accessDeniedPage("/error")
//                .and().httpBasic()
        ;
    }



//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails admin = User.builder()
//                .username("mourad")
//                .password(passwordEncoder.encode("admin"))
//                .password("{noop}admin")
//                .roles("ADMIN")
//                .roles(AppUserRole.ADMIN.name())
//                .authorities(AppUserRole.ADMIN.getGrantedAuthorities())
//                .build();
//
//        UserDetails user1 = User.builder()
//                .username("yassir")
//                .password(passwordEncoder.encode("adham"))
//                .password("{noop}adham")
//                .roles("USER")
//                .roles(AppUserRole.USER.name())
//                .authorities(AppUserRole.USER.getGrantedAuthorities())
//                .build();
//
//        return new InMemoryUserDetailsManager(
//                admin,
//                user1
//        );
//    }
}
