package softuni.banksters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BankstersApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankstersApplication.class, args);
    }

}
