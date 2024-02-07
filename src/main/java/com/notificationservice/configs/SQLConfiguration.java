package com.notificationservice.configs;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.notificationservice.repository.sql")
public class SQLConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.sql")
    public DataSourceProperties sqlDataSourceProperties () {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.sql")
    @Primary
    public DataSource getSQLDataSource() {
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.url("jdbc:mysql://localhost:3306/db");
//        dataSourceBuilder.username("root");
//        dataSourceBuilder.password("12345678");

        return sqlDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

//    @Bean(name = "sqlEntityManagerFactory")
//    @Primary
//    public LocalContainerEntityManagerFactoryBean sqlEntityManagerBean(EntityManagerFactoryBuilder builder) {
//
//    }
}

//spring.datasource.url=jdbc:mysql://localhost:3306/db
//spring.datasource.username=root
//spring.datasource.password=12345678

//spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect
//spring.jpa.hibernate.ddl-auto=update
