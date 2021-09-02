package net.myfarm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Set exception for security
    @Override
    public void configure(WebSecurity web) throws Exception {

        // When not apply security
        web
                .ignoring()
                .antMatchers("/webjars/**")
                .antMatchers("/css/**")
                .antMatchers("/js/**")
                .antMatchers("/h2-console/**");
    }

    // Configuration for security
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Configuration for pages without login
        http
                .authorizeRequests()
                .antMatchers("/login").permitAll() // Direct link is okay
                .antMatchers("/users/signup").permitAll() // Direct link is okay
                .antMatchers("/user/signup/rest").permitAll() // Direct link is ok
                .antMatchers("/admin").hasAuthority("ROLE_ADMIN") // Authority restriction
                .anyRequest().authenticated(); // Except this, direct link is okay

        // Login
        http
                .formLogin()
                        .loginProcessingUrl("/login") // Path to login
                        .loginPage("/login") // Assign Login page
                        .failureUrl("/login?error") // Redirect to login failure page if unssucccessful
                        .usernameParameter("userId") // User ID for login page
                        .passwordParameter("password")  // Password for login page
                        .defaultSuccessUrl("/user/list", true);// Redirect if successful

        // Logout
        http
                .logout()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout");

        // Disable CSRF (temporarily)
        //http.csrf().disable();
    }

    // Authorization
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        PasswordEncoder encoder = passwordEncoder();
        // Main memory authorization
        /*
        auth
                .inMemoryAuthentication()
                .withUser("user") // Add User
                .password(encoder.encode("user"))
                .password("user")
                .roles("GENERAL")
                .and()
                .withUser("admin") // Add admin
                .password(encoder.encode("admin"))
                .password("admin")
                .roles("ADMIN");
         */

        // Authorization with User data
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder);
    }
}
