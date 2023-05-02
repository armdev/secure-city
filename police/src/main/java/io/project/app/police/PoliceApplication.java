package io.project.app.police;

import io.project.app.police.domain.PoliceCar;
import io.project.app.police.repositories.PoliceCarJpaRepository;
import io.project.app.police.repositories.PoliceOfficerJpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan(basePackages = {"io.project"})
@Slf4j
@EnableJpaRepositories(basePackages = {
    "io.project.app.police.repositories"})
@EntityScan("io.project.app.police.domain")
public class PoliceApplication {

    public static void main(String[] args) {
        final SpringApplication application = new SpringApplication(PoliceApplication.class);
        application.setBannerMode(Banner.Mode.CONSOLE);
        application.setWebApplicationType(WebApplicationType.SERVLET);
        application.run(args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedMethods(CorsConfiguration.ALL)
                        .allowedHeaders(CorsConfiguration.ALL)
                        .allowedOriginPatterns(CorsConfiguration.ALL);
            }
        };
    }

    @Autowired
    private PoliceOfficerJpaRepository policeOfficerJpaRepository;

    @Autowired
    private PoliceCarJpaRepository policeCarJpaRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional(transactionManager = "transactionManager")
    public void init() {
        List<PoliceCar> cars = policeCarJpaRepository.findAll();
        if (cars.isEmpty()) {
            PoliceCar policeCar = new PoliceCar("FORD", "Police Interceptor", 2022, "Patrol", "DD-101", LocalDateTime.now());
            policeCarJpaRepository.save(policeCar);
            policeCar = new PoliceCar("BMW", "Police Interceptor", 2018, "Patrol", "DD-203", LocalDateTime.now());
            policeCarJpaRepository.save(policeCar);
            policeCar = new PoliceCar("AUDI", "Police Interceptor", 2023, "Patrol", "DD-605", LocalDateTime.now());
            policeCarJpaRepository.save(policeCar);
            policeCar = new PoliceCar("FERRARI", "Police Interceptor", 2015, "Patrol", "DD-777", LocalDateTime.now());
            policeCarJpaRepository.save(policeCar);
            long count = policeCarJpaRepository.count();
            log.info("Count of cars  " + count);

        }

    }
}
