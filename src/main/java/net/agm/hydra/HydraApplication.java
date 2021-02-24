package net.agm.hydra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;





@SpringBootApplication//(exclude = { SecurityAutoConfiguration.class })
@EnableAuthorizationServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class HydraApplication {

	public static void main(String[] args) {
		SpringApplication.run(HydraApplication.class, args);
	}

	
}
