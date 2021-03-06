package com.codeup.qshe.configuration;


import com.codeup.qshe.services.user.SimpleSocialUsersDetailService;
import com.codeup.qshe.services.user.UserDetailsLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsLoader usersLoader;

    @Autowired
    public SecurityConfiguration(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usersLoader) // How to find users by their username
                .passwordEncoder(passwordEncoder()) // How to encode and verify passwords
        ;
    }


    // Cross Browser AJAX request checks
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Bean //Redirects to last page
    public AuthenticationSuccessHandler successHandler() {
        SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
        handler.setUseReferer(true);
        return handler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                /* Login configuration */
                .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?error=true")
                    .successHandler(successHandler())
                    .defaultSuccessUrl("/users/displayprofile") // user's home page, it can be any URL
                    .permitAll() // Anyone can go to the login page
                    /* Logout configuration */
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/logout.done")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/login?logout") // append a query string value
                    /* Pages that can be viewed without having to log in */
                .and()
                    .authorizeRequests()
                        .antMatchers("/sign-up","/viewstate","/posts/topic","/css/**", "js/**","/img/**","/error**") // anyone can see the home and the posts pages
                        .permitAll()
                    .and()
                     .authorizeRequests()
                     .antMatchers("/bill/**","/profile","/plaid/**","/viewstate")
                     .hasAuthority("USER")
                    /* Pages that require authentication */
                .and()
                    .authorizeRequests()
                    .antMatchers(
                            "/static/**",
                            "/viewstate/**",
                            "/messages/create",
                            "/topics"
                    )
                .authenticated()
                .and()
                .rememberMe()
                .and()
                .apply(new SpringSocialConfigurer()
                        .postLoginUrl("/users/displayprofile")
                        .alwaysUsePostLoginUrl(true));

    }

    @Bean
    public SocialUserDetailsService socialUsersDetailService() {
        return new SimpleSocialUsersDetailService(usersLoader);
    }

}
