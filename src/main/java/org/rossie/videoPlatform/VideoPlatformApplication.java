package org.rossie.videoPlatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.rossie")
public class VideoPlatformApplication {

    public static void main(String[] args) {

        SpringApplication.run(VideoPlatformApplication.class, args);
    }

}
