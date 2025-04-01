package com.co.booking_ai.service;

//import com.co.taxislibres.common.BaseCommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.text.NumberFormat;
import java.util.TimeZone;

//@BaseCommonService
@SpringBootApplication
@EnableSwagger2
@Slf4j
public class BaseApplication {

    @Autowired
    private ApplicationContext appContext;

    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }

    public void printInfo() throws Exception {
        Runtime runtime = Runtime.getRuntime();
        final NumberFormat format = NumberFormat.getInstance();
        final long maxMemory = runtime.maxMemory();
        final long allocatedMemory = runtime.totalMemory();
        final long freeMemory = runtime.freeMemory();
        final long mb = 1024L * 1024L;
        final String mega = " MB";
        final Environment env = appContext.getEnvironment();
        String protocol = "http";
        if ("true".equals(env.getProperty("server.ssl.enabled"))) {
            protocol = "https";
        }

        log.info("========================== Memory Info ==========================");
        log.info("Free memory: {}", format.format(freeMemory / mb) + mega);
        log.info("Allocated memory: {}", format.format(allocatedMemory / mb) + mega);
        log.info("Max memory: {}", format.format(maxMemory / mb) + mega);
        log.info("Total free memory: {}", format.format((freeMemory + (maxMemory - allocatedMemory)) / mb) + mega);
        log.info("Application '{}' is running! Access URLs:", env.getProperty("spring.application.name"));
        log.info("Local: {}://localhost:{}", protocol, env.getProperty("server.port"));
        log.info("External: {}://{}:{}", protocol, InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"));
        log.info("Root Path: {}", env.getProperty("server.servlet.context-path"));
//		log.info("Profile(s): {}", env.getActiveProfiles());
        log.info("=================================================================");
    }

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Bean
    public CommandLineRunner memInfoRunner() {
        return args -> printInfo();
    }


}