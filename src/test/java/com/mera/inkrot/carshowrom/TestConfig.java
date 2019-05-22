package com.mera.inkrot.carshowrom;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@ComponentScan(basePackages = { "com.mera.inkrot.carshowroom.service" })
@Configuration
@EnableJpaRepositories(basePackages = "com.mera.inkrot.carshowroom.repository")
@PropertySource("application.properties")
@EnableTransactionManagement
public class TestConfig {
}