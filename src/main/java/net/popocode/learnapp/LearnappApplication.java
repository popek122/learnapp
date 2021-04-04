package net.popocode.learnapp;

import net.popocode.learnapp.controllers.LearnController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class LearnappApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx=SpringApplication.run(LearnappApplication.class, args);
        LearnController controller=ctx.getBean(LearnController.class);
        controller.mainLoop();
    }

    @Bean
    Scanner scanner() {
        return new Scanner(System.in);
    }
}
