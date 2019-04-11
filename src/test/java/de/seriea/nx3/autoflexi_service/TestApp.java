package de.seriea.nx3.autoflexi_service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.seriea.nx3.core.Nx3Application;
import de.seriea.nx3.core.application.properties.ApplicationConfigurationProperties;
import de.seriea.nx3.core.docgen.Nx3DocumentGenerator;
import de.seriea.nx3.core.email.Nx3EmailConfiguration;
import de.seriea.nx3.core.email.Nx3EmailService;
import de.seriea.nx3.core.i18n.Nx3TranslationMessageSource;
import de.seriea.nx3.core.jpa.Nx3RepositoryFactoryBean;
import de.seriea.nx3.core.model.shared.ActivityStreamRepository;
import de.seriea.nx3.core.oauth.IAMClient;
import de.seriea.nx3.core.service.DocumentTemplateService;
import de.seriea.nx3.core.test.IAMClientMock;
import de.seriea.nx3.core.test.Nx3EmailServiceMock;

/**
 * Test version of the application, where external dependencies are mocked out partly.
 */
@SpringBootApplication
@ComponentScan(basePackages = { "de.seriea" })
@EnableJpaRepositories(basePackages = { "de.seriea" }, repositoryFactoryBeanClass = Nx3RepositoryFactoryBean.class)
public class TestApp extends Nx3Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TestApp.class, args);
	}

	@Override
	@Bean(name="emailService")
	public Nx3EmailService emailService(Nx3DocumentGenerator documentGenerator,
			Nx3EmailConfiguration emailConfiguration, Nx3TranslationMessageSource nx3TranslationMessageSource,
			DocumentTemplateService documentTemplateService, ActivityStreamRepository activityStreamRepository) {

		return new Nx3EmailServiceMock();
	}

	@Override
	@Bean(name = "oauthClient")
	@Profile("oauthClient")
	public IAMClient iamClient(@Value("${security.oauth2.client.client-id}") String oauthClientId,
			ApplicationConfigurationProperties applicationConfigurationProperties, ObjectMapper om) {
		return new IAMClientMock();
	}
}
