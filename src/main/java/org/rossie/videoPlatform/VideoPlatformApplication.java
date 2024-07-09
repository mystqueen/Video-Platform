package org.rossie.videoPlatform;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.rossie")
public class VideoPlatformApplication {

    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.load();
        System.setProperty("azure.storage.account-key", dotenv.get("AZURE_STORAGE_ACCOUNT_KEY"));
        SpringApplication.run(VideoPlatformApplication.class, args);
    }

}
