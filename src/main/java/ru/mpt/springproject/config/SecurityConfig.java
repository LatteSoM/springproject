package ru.mpt.springproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.mpt.springproject.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers("/departments/**").hasAnyRole("ADMIN", "USER", "HR")
                .requestMatchers("/document-types/**").hasAnyRole("ADMIN", "USER", "HR")
                .requestMatchers("/positions/**").hasRole("HR")
                .requestMatchers("/users/**").hasRole("ADMIN")
                .requestMatchers("/employees/**").hasRole("HR")
                .requestMatchers("/employees-positions/**").hasRole("HR")
                .requestMatchers("/documents/**").hasAnyRole("USER","HR")
                .requestMatchers("/projects/**").hasAnyRole("USER","HR", "ADMIN")
                .requestMatchers("/tasks/**").hasAnyRole("USER","HR", "ADMIN")
                .requestMatchers("/payrolls/**").hasAnyRole("USER","HR", "ADMIN")
                .requestMatchers("/work-times/**").hasAnyRole("HR", "ADMIN")
                .requestMatchers("/test/**").hasAnyRole("HR", "ADMIN", "USER")
                .anyRequest().authenticated());
        http.formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll());
        http.logout(Customizer.withDefaults());
        return http.build();
        /*
        <a href="/login" class="btn btn-primary btn-block mb-2">Login</a>
            <a href="/departments" class="btn btn-primary btn-block mb-2">Departments</a>
            <a href="/document-types" class="btn btn-primary btn-block mb-2">Document Types</a>
            <a href="/positions" class="btn btn-primary btn-block mb-2">Positions</a>
            <a href="/users" class="btn btn-primary btn-block mb-2">Users</a>
            <a href="/employees" class="btn btn-primary btn-block mb-2">Employees</a>
            <a href="/employee-positions" class="btn btn-primary btn-block mb-2">Employee Positions</a>
            <a href="/documents" class="btn btn-primary btn-block mb-2">Documents</a>
            <a href="/projects" class="btn btn-primary btn-block mb-2">Projects</a>
            <a href="/tasks" class="btn btn-primary btn-block mb-2">Tasks</a>
            <a href="/payrolls" class="btn btn-primary btn-block mb-2">Payrolls</a>
            <a href="/work-times" class="btn btn-primary btn-block mb-2">Work Times</a>
         */
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
