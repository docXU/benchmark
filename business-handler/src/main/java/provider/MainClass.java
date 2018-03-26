package provider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCaching()
public class MainClass {
    public static void main(String[] args) {
        SpringApplication.run(MainClass.class, args);
        try {
            //阻塞
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}