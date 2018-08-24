package hellocloud;

import hellocloud.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SwaggerConfig.class)
public class FundsManagerApplication {
    public static final void main(String... args) {
        SpringApplication.run(FundsManagerApplication.class, args);
    }
}
