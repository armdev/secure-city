package io.project.app.police;

import io.project.app.police.domain.PoliceCar;
import io.project.app.police.domain.PoliceOfficer;
import io.project.app.police.helpers.PoliceCarGenerator;
import io.project.app.police.helpers.PoliceOfficerGenerator;
import io.project.app.police.services.PoliceCarService;
import io.project.app.police.services.PoliceOfficerService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan(basePackages = {"io.project"})
@Slf4j
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
    private PoliceOfficerService policeOfficerService;
    
    @Autowired
    private PoliceCarService policeCarService;

   //// @EventListener(ApplicationReadyEvent.class)
    public void init() {

        List<PoliceCar> generateCars = PoliceCarGenerator.generateCars(50);
        policeCarService.saveAll(generateCars);
        List<PoliceOfficer> generateOfficers = PoliceOfficerGenerator.generateOfficers(150);
        policeOfficerService.saveAll(generateOfficers);

    }
}
