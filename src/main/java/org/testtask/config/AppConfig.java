package org.testtask.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"org.testtask.dao.impl",
        "org.testtask.factory",
        "org.testtask.repository",
        "org.testtask.service",
        "org.testtask.main"})
public class AppConfig {
}
