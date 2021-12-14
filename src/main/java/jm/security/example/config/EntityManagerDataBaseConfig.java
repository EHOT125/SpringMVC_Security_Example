package jm.security.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("jm")
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class EntityManagerDataBaseConfig {
    @Autowired
    Environment environment;

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        return properties;
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

        driverManagerDataSource.setDriverClassName(environment.getRequiredProperty("db.driver"));
        driverManagerDataSource.setUrl(environment.getRequiredProperty("db.url"));
        driverManagerDataSource.setUsername(environment.getRequiredProperty("db.username"));
        driverManagerDataSource.setPassword(environment.getRequiredProperty("db.password"));

        return driverManagerDataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean buildEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean container = new LocalContainerEntityManagerFactoryBean();
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();

        container.setDataSource(getDataSource());
        container.setPackagesToScan("jm");
        container.setJpaVendorAdapter(adapter);
        container.setJpaProperties(getProperties());

        return container;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(buildEntityManagerFactory().getObject());

        return transactionManager;
    }
}

