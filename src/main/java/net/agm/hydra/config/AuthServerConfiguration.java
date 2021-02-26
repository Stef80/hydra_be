package net.agm.hydra.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class AuthServerConfiguration implements AuthorizationServerConfigurer {

	@Value("${spring.security.oauth2.client.client-id}")
	private String clientId;

	@Value("${spring.security.oauth2.client.client-secret}")
	private String clientSecret;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	DataSource data;

	@Autowired 
	AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.checkTokenAccess("isAuthenticated()").tokenKeyAccess("permitAll()")
		  .allowFormAuthenticationForClients() ;
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
		.withClient(clientId)
		.secret(passwordEncoder.encode(clientSecret))
		.scopes("read", "write")
		.authorizedGrantTypes("password", "authorization_code","refresh_token")
		.redirectUris("localhost:9090/login")
		.accessTokenValiditySeconds(36000)
		.refreshTokenValiditySeconds(7640000);
		//clients.jdbc(data).passwordEncoder(passwordEncoder);
	}


	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore())
		.accessTokenConverter(tokenEnhancer())
		.authenticationManager(authenticationManager);

	}
	/*
     *  bean che mi genera a runtime le chiavi pubbliche e private
     *  e jwtAccessTokenConverter() che definisce come il token verrà
        tradotto, e quale sarà la chiave di cifratura simmetrica che verrà usata per firmare i token
        e consentirne la verifica
     */
    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
     
        KeyPairGenerator kpg = null;
        try {
            kpg = KeyPairGenerator.getInstance("RSA");
    
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(kp);
        return converter;
       
    }
    @Bean // bean da utlizzare per generare il token jwt
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }

}
