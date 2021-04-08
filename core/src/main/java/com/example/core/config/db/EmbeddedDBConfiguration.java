package com.example.core.config.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

import static com.example.common.DbConstants.*;

/**
 * Created by Alexandr Lobodedov on 28.04.2016.
 */
@Configuration
public class EmbeddedDBConfiguration {

    @Bean(name = DATABASE_PROPERTIES_BEAN)
    @ConfigurationProperties(DATABASE_PROPERTIES)
    public HikariConfig searchHikariConfig() {
        Properties properties = new Properties();
        properties.put("useSSL", false);
        properties.put("rewriteBatchedStatements", true);
        properties.put("cachePrepStmts", true);
        properties.put("prepStmtCacheSize", 250);
        properties.put("prepStmtCacheSqlLimit", 2048);
        properties.put("useServerPrepStmts", true);
        properties.put("useLocalSessionState", true);
        properties.put("useLocalTransactionState", true);
        properties.put("cacheResultSetMetadata", true);
        properties.put("cacheServerConfiguration", true);
        properties.put("elideSetAutoCommits", true);
        properties.put("maintainTimeStats", false);
        properties.put("characterEncoding", "UTF-8");

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setUsername("sa");
        hikariConfig.setMaxLifetime(25000);
        hikariConfig.setJdbcUrl("jdbc:hsqldb:mem:embedded;shutdown=true;hsqldb.write_delay_millis=0;readonly=true;");
        hikariConfig.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
        hikariConfig.setMinimumIdle(8);
        hikariConfig.setMaximumPoolSize(8);
        hikariConfig.setDataSourceProperties(properties);

        return hikariConfig;
    }

    @Bean(name = DATA_SOURCE)
    public DataSource searchServiceDataSource(@Qualifier(DATABASE_PROPERTIES_BEAN) HikariConfig hikariConfig) {
        return new HikariDataSource(hikariConfig);
    }

    @Bean(name = JPA_VENDOR_ADAPTER)
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabasePlatform("org.hibernate.dialect.HSQLDialect");
        adapter.setShowSql(true);

        return adapter;
    }

    @Bean(name = JPA_PROPERTIES)
    public Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.max_fetch_depth", 3);
        properties.put("hibernate.jdbc.fetch_size", 50);
        properties.put("hibernate.jdbc.batch_size", 10);
        properties.put("org.hibernate.flushMode", "COMMIT");
        properties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");

        return properties;
    }
}
