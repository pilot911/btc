package com.example.common;

public interface DbConstants {

    String JPA_VENDOR_ADAPTER = "jpaVendorAdapter";
    String JPA_PROPERTIES = "jpaProperties";
    String PACKAGES_TO_SCAN = "com.example.core.dao";

    String ENTITY_MANAGER_FACTORY = "entityManagerFactory";
    String TRANSACTION_MANAGER = "serviceTransactionManager";
    String DATA_SOURCE = "serviceDataSource";
    String JDBC_TEMPLATE = "serviceJdbcTemplate";
    String NAMED_JDBC_TEMPLATE = "serviceNamedJdbcTemplate";
    String PERSISTENCE_UNIT_NAME = "persistenceUnitName";
    String DATABASE_PROPERTIES = "demo.datasource";
    String DATABASE_PROPERTIES_BEAN = "dataSourceProperties";
    String SPRING_LIQUIBASE_BEAN = "demoLiquibase";
    String LIQUIBASE_PROPERTIES_BEAN = "demoLiquibaseProperties";
    String LIQUIBASE_PROPERTIES = "demo.liquibase";

    interface Table {

        String ID = "ID";
        String NAME = "NAME";

        interface TransactionHistory {
            String TABLE_NAME = "transaction_history";
        }
    }
}
