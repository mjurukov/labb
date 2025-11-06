package mk.ukim.finki.wp.labb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class LabbApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabbApplication.class, args);
    }

}
