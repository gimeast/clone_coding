package gimeast.project02.config;

import gimeast.project02.security.filter.ApiCheckFilter;
import gimeast.project02.security.filter.ApiLoginFilter;
import gimeast.project02.security.handler.ApiLoginFailHandler;
import gimeast.project02.security.handler.SocialLoginSuccessHandler;
import gimeast.project02.security.service.UserService;
import gimeast.project02.security.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Log4j2
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth-> {
            auth.requestMatchers("/notes/**").permitAll();
            auth.requestMatchers("/api/login").permitAll();
            auth.requestMatchers("/sample/**").permitAll();
        });

        http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        http.oauth2Login(login -> {
            login.successHandler(customSuccessHandler());
        });
        http.rememberMe(rememberMe -> {
            rememberMe.tokenValiditySeconds(60 * 60 * 24 * 7);
        });

        AuthenticationManagerBuilder sharedObject = http.getSharedObject(AuthenticationManagerBuilder.class);
        AuthenticationManager authManager = sharedObject.build();

        //ApiLoginFilter -> AbstractAuthenticationProcessingFilter는 AuthenticationManager가 필요
        http.authenticationManager(authManager);

        //ApiLoginFilter
        ApiLoginFilter apiLoginFilter = getApiLoginFilter(authManager);
        http.addFilterBefore(apiLoginFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private ApiLoginFilter getApiLoginFilter(AuthenticationManager authManager) {
        ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/api/login", jwtUtil());
        apiLoginFilter.setAuthenticationManager(authManager);
        apiLoginFilter.setAuthenticationFailureHandler(new ApiLoginFailHandler());
        return apiLoginFilter;
    }

    @Bean
    public JWTUtil jwtUtil() {
        return new JWTUtil();
    }

    @Bean
    public AuthenticationSuccessHandler customSuccessHandler() {
        return new SocialLoginSuccessHandler(passwordEncoder());
    }

    @Bean
    public OncePerRequestFilter apiCheckFilter() {
        return new ApiCheckFilter("/notes/**", jwtUtil());
    }

/*
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("user1")
                .password(passwordEncoder().encode("1111"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("1111"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
*/

}
