package runner;

import bigbang.i.IBusinessService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by Matt Xu on 2018/3/22
 */

@SpringBootApplication
public class App {
    public static void main(String[] args){
        ConfigurableApplicationContext run = SpringApplication.run(App.class, args);
        System.out.println(run.getBean(IBusinessService.class).qurey("1").toString());
    }
}
