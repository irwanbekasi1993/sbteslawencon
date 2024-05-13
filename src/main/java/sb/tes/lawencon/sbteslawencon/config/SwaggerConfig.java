package sb.tes.lawencon.sbteslawencon.config;

import java.util.Arrays;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
public class SwaggerConfig {
@Bean
	public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.cors(cors->corsConfigurationSource())
		.csrf(csrf->csrf.disable())
		.authorizeHttpRequests((request)->request.requestMatchers("/sbteslawencon/api/v1/**","/swagger-ui/**","/swagger-ui.html","/webjars/**","/v2/**","/swagger-resources/**","/v3/api-docs/**").
            permitAll()
			.anyRequest().authenticated());
		return httpSecurity.build();
    }


	@Bean
	public GroupedOpenApi api(){
		return GroupedOpenApi.builder().group("sbteslawencon").pathsToMatch("/sbteslawencon/api/v1/**").build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource(){
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
		corsConfiguration.setAllowedMethods(Arrays.asList("*"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return urlBasedCorsConfigurationSource;
	}

	

}
