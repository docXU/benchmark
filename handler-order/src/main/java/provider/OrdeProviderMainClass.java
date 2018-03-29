package provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Matt Xu on 2018/3/29
 */

@SpringBootApplication
@ImportResource("classpath:dubbo/order-provider.xml")
public class OrdeProviderMainClass {
    public static void main(String args[]) {
        SpringApplication.run(OrdeProviderMainClass.class, args);
        try {
            //阻塞
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
