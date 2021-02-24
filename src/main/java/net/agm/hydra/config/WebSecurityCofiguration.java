package net.agm.hydra.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import net.agm.hydra.services.impl.UserDetailsServiceImpl;



@Configuration
@EnableWebSecurity
public class WebSecurityCofiguration extends WebSecurityConfigurerAdapter {


	@Autowired
	private UserDetailsServiceImpl userDetailsService;


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public AuthenticationManager getAuthenticationManager() throws Exception {
		return super.authenticationManagerBean();
	}


	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers("/oauth/authorize**","/login","/authenticate");
	}

	   @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	                .cors()
	                .and()
	                .csrf().disable()
	                .authorizeRequests()
	                .antMatchers("/api/user")
	                .permitAll();
	                
	   }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
		.and()
		.csrf().disable()
//		.authorizeRequests()
//		.antMatchers("/oauth/authorize**","/login","/authenticate").permitAll()
//		.anyRequest()
//		.authenticated()
//		.and()
//		.formLogin()
//		.permitAll()
//		.and()
//		.logout().permitAll()
//		.and()
//		.oauth2ResourceServer(oauth -> oauth.jwt())
		;
	}


	//
	//	@Bean
	//    public CorsConfigurationSource corsConfigurationSource()
	//    {
	//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	//        CorsConfiguration configuration = new CorsConfiguration();
	//        configuration.setAllowCredentials(true);
	//        configuration = configuration.applyPermitDefaultValues();
	//        //configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
	//        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","PATCH","OPTIONS","HEAD"));
	//        source.registerCorsConfiguration("/**", configuration);
	//        return source;
	//    }
	
	
}

