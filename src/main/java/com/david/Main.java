package com.david;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * This class defines the entry point of the application.
 */
@SpringBootApplication
public class Main {

    /**
     * Entry point of the application.
     *
     * @param args The command line arguments.
     * @throws Exception Any Exception thrown inside the application.
     */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }
}