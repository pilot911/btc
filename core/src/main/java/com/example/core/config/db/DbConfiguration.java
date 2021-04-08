package com.example.core.config.db;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

import static com.example.common.DbConstants.*;


/**
 * @author Artem Krotov
 * Created on 20.03.2018.
 * TODO: Поменять нейминги в DbConstants (убрать searchservice)
 */
@Configuration
@EnableJpaRepositories(
        basePackages = {PACKAGES_TO_SCAN},
        entityManagerFactoryRef = ENTITY_MANAGER_FACTORY,
        transactionManagerRef = TRANSACTION_MANAGER
)
public class DbConfiguration {
    private final DataSource dataSource;

    public DbConfiguration(@Qualifier(DATA_SOURCE) DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Primary
    @Bean(name = ENTITY_MANAGER_FACTORY)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier(JPA_VENDOR_ADAPTER) JpaVendorAdapter jpaVendorAdapter,
                                                                       @Qualifier(JPA_PROPERTIES) Properties jpaProperties) {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setJpaVendorAdapter(jpaVendorAdapter);
        factory.setPackagesToScan(PACKAGES_TO_SCAN);
        factory.setJpaProperties(jpaProperties);
        factory.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);

        return factory;
    }

    @Bean(name = TRANSACTION_MANAGER)
    public JpaTransactionManager jpaTransactionManager(@Qualifier(ENTITY_MANAGER_FACTORY) EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean(name = JDBC_TEMPLATE)
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = NAMED_JDBC_TEMPLATE)
    public NamedParameterJdbcTemplate namedJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean(name = LIQUIBASE_PROPERTIES_BEAN)
    @ConfigurationProperties(prefix = LIQUIBASE_PROPERTIES, ignoreUnknownFields = false)
    public LiquibaseProperties liquibaseProperties() {
        return new LiquibaseProperties();
    }

    @Bean(SPRING_LIQUIBASE_BEAN)
    @ConfigurationProperties(prefix = LIQUIBASE_PROPERTIES)
    public SpringLiquibase semLiquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        final LiquibaseProperties liquibaseProperties = liquibaseProperties();
        liquibase.setChangeLog(liquibaseProperties.getChangeLog());
        liquibase.setShouldRun(liquibaseProperties.isEnabled());
        liquibase.setDropFirst(liquibaseProperties.isDropFirst());

        return liquibase;
    }
}
