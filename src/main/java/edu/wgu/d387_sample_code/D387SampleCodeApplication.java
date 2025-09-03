package edu.wgu.d387_sample_code;

import edu.wgu.d387_sample_code.localization.WelcomeDisplayThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Locale;

@SpringBootApplication
public class D387SampleCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(D387SampleCodeApplication.class, args);
    }

    @PostConstruct
    public void displayWelcomeMessages() {
        Thread englishThread = new WelcomeDisplayThread(Locale.CANADA);
        Thread frenchThread = new WelcomeDisplayThread(Locale.CANADA_FRENCH);

        englishThread.start();
        frenchThread.start();

        try {
            englishThread.join();
            frenchThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread join interrupted: " + e.getMessage());
        }
    }
}