package org.java.blissful.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
public class AuthConf {

	@Bean
	PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
		
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		return 
			http.csrf(c -> c.disable())
				.authorizeHttpRequests(a -> a
						.requestMatchers("/**", "/api/**").permitAll()
			).formLogin(f -> f.defaultSuccessUrl("/").permitAll()
			).logout(l -> l.logoutSuccessUrl("/")
			).build();
		
	}


    @Bean
    MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
	
}
