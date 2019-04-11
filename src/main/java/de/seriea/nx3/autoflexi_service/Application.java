package de.seriea.nx3.autoflexi_service;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import de.seriea.nx3.core.Nx3Application;
import de.seriea.nx3.core.bpm.plugins.RessourcePluginsRegistrationPlugin;
import de.seriea.nx3.core.bpm.plugins.watchoverdue.ExecutionOverdueEscalationService;
import de.seriea.nx3.core.bpm.plugins.watchoverdue.WatchTaskOverdueDatePlugin;
import de.seriea.nx3.core.jpa.Nx3RepositoryFactoryBean;

@SpringBootApplication
//@EnableSwagger2
@ComponentScan(basePackages = { "de.seriea" })
@EnableJpaRepositories(basePackages = { "de.seriea" }, repositoryFactoryBeanClass = Nx3RepositoryFactoryBean.class)
public class Application extends Nx3Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RessourcePluginsRegistrationPlugin ressourcePluginsRegistrationPlugin(
			WatchTaskOverdueDatePlugin watchTaskOverdueDatePlugin) {
		List<ProcessEnginePlugin> plugins = new ArrayList<>();
		// add plugins here
		return new RessourcePluginsRegistrationPlugin(plugins);
	}

	@Bean
	public WatchTaskOverdueDatePlugin watchTaskOverdueDatePlugin(ApplicationContext appcontext) {
		List<ExecutionOverdueEscalationService> taskOverdueEscalationServices = new ArrayList<>();
		// add plugins here
		return new WatchTaskOverdueDatePlugin(taskOverdueEscalationServices, appcontext);
	}
}
