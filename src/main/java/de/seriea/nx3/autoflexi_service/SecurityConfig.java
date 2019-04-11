package de.seriea.nx3.autoflexi_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import de.seriea.nx3.core.security.DefaultEndpointSecurityConfig;

/**
 * Enables the OAuth2 single sign on using @EnableOAuth2Sso .
 * Enables Spring Security global method security using @EnableGlobalMethodSecurity .
 * Default NX3 endpoint security is applied by calling {@link DefaultEndpointSecurityConfig}.
 * 
 * @author dpeer
 */
@Configuration
@EnableOAuth2Sso
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DefaultEndpointSecurityConfig defaultEndpointSecurityConfig;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		String[] additionallyPermittedPaths = {};		
		defaultEndpointSecurityConfig.configure(http, additionallyPermittedPaths);
	}
}