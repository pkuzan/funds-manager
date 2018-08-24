package hellocloud;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

//@EnableJpaRepositories
@EntityScan("hellocloud.model")
@ComponentScan({"hellocloud", "hellocloud.dao"})
public class FundServiceTestConfig {
}
