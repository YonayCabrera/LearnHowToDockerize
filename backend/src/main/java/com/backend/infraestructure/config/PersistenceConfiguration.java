package com.backend.infraestructure.config;

import com.backend.repository.BackEndRepository;
import com.backend.repository.BackEndRepositoryPostgreSql;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class PersistenceConfiguration {
    @Value("jdbc:postgresql://localhost:8000/backend")
    private String connection;

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public BackEndRepository dataSource(){
        return new BackEndRepositoryPostgreSql(connection);
    }
}

