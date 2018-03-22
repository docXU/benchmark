package dubbo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:dubbo/consumer.xml")
@SpringBootApplication
public class ConsumerMainClass {


    public static void main(String args[]){
        //测试常规服务
        ConfigurableApplicationContext run = SpringApplication.run(ConsumerMainClass.class,args);
        System.out.println(run.getBean(IService.class).getUser("xxw").get(0));
    }
}
