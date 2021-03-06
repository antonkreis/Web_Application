package App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableCaching
@EnableScheduling
@EnableAsync
//@ComponentScan("App.*")
//@EnableJpaRepositories(basePackageClasses = {BusTicketRepository.class, PlaneTicketRepository.class, TrainTicketRepository.class, JPARepository.class})
//@EnableJpaRepositories(basePackages = {"database"})
//@EntityScan(basePackages = {"App.web.*", "App.model.*"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
