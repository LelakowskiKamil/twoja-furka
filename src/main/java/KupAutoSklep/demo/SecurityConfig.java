package KupAutoSklep.demo;

import KupAutoSklep.demo.domain.model.login.User;
import KupAutoSklep.demo.domain.repository.UserRepository;
import KupAutoSklep.demo.service.CustomUserDetailService;
import KupAutoSklep.demo.service.UserDisplayDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailService customUserDetailService;
    private final UserRepository userRepository;
    public SecurityConfig(CustomUserDetailService customUserDetailService, UserRepository userRepository) {
        this.customUserDetailService = customUserDetailService;
        this.userRepository = userRepository;
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder());
        auth.setUserDetailsService(customUserDetailService);
        return auth;

}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/newoffer")
                .hasRole("USER")
                .antMatchers("/newoffer")
                .hasRole("ADMIN")
                .antMatchers("/newoffer/*")
                .hasRole("USER")
                .antMatchers("/newoffer/*")
                .hasRole("ADMIN")
                .antMatchers("/editoffer/*")
                .hasRole("ADMIN")
                .antMatchers("/deleteoffer/*")
                .hasRole("ADMIN")
                .antMatchers("/registation","/login")
                .permitAll()
                .antMatchers("**/h2-console/**").permitAll()
        .and()
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .and()
        .logout()
        .invalidateHttpSession(true)
        .clearAuthentication(true)
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/login?logout");

    }

    public User findByEmailAddress(String emailAddress){
        return userRepository.findByEmail(emailAddress);
    }
}
