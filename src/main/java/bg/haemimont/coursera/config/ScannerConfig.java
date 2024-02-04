package bg.haemimont.coursera.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class ScannerConfig {

    @Bean
    public Scanner scannerInitializer() {
        return new Scanner(System.in);
    }
}
