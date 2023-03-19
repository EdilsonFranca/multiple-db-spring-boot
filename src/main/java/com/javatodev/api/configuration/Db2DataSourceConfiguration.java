package com.javatodev.api.configuration;

import com.javatodev.api.model.db1.UserEntity1;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.javatodev.api.repository.db2",
            entityManagerFactoryRef = "db2EntityManagerFactory",
              transactionManagerRef = "db2TransactionManager")
public class Db2DataSourceConfiguration {
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.db2")
    public DataSourceProperties db2DatasourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.db2.configuration")
    public DataSource userDataSource() {
        return db2DatasourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "db2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(userDataSource())
                .packages(UserEntity1.class)
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager db2TransactionManager(final @Qualifier("db2EntityManagerFactory") LocalContainerEntityManagerFactoryBean db2EntityManagerFactory) {
        return new JpaTransactionManager(db2EntityManagerFactory.getObject());
    }
}
