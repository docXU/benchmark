package provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AdviceMode;

@SpringBootApplication
@EnableCaching
public class BusinessProviderMainClass {
    public static void main(String[] args) {
        SpringApplication.run(BusinessProviderMainClass.class, args);
        try {
            //阻塞
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}