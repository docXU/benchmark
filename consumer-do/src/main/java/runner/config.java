package runner;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Matt Xu on 2018/3/22
 */

@Configuration
@ImportResource({ "classpath:dubbo/*.xml" })
public class config {
}
