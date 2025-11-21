package ws.beauty.salon.configuration; 

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ws.beauty.salon.repository.UserRepository;
import static org.springframework.security.config.Customizer.withDefaults;
import java.util.Collections;
import ws.beauty.salon.model.User; 
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Configuration
public class SecurityConfig {

        private final UserRepository userRepository;
                public SecurityConfig(UserRepository userRepository) {
                this.userRepository = userRepository;
        }
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                auth -> auth.requestMatchers("/signin", "/signup").permitAll()
                        .requestMatchers("/api/v1/stylist/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/stylist-service/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/api/v1/reschedule-request/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated())
                .httpBasic(withDefaults()).csrf(csrf -> csrf.disable())
                //.formLogin(withDefaults())
                //.rememberMe(withDefaults())
                .logout(logout -> logout.logoutUrl("/signout").permitAll());
        return http.build();
        } 
      
        // Contraseñas sin cifrar (para pruebas)
        @SuppressWarnings("deprecation")
        @Bean
        public PasswordEncoder mipasswordEncoder() {
        return org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
        }

        // Carga el usuario desde la base de datos
        @Bean
         public UserDetailsService userDetailsService() {
                return username -> {
                        User usuario = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

                String roleName = usuario.getRole();
                        if (!roleName.startsWith("ROLE_")) {
                                roleName = "ROLE_" + roleName;
                        }
                        return org.springframework.security.core.userdetails.User.builder()
                                .username(usuario.getUsername())
                                .password(usuario.getPassword())
                                .authorities(Collections.singletonList(new SimpleGrantedAuthority(roleName)))
                                .build();
                        };
                }

}
