package provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:dubbo/shopper-provider.xml")
@SpringBootApplication
public class ShopperProviderMainClass {
    public static void main(String args[]) {
        SpringApplication.run(ShopperProviderMainClass.class, args);
        try {
            //阻塞
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
