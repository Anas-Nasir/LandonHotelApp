package edu.wgu.d387_sample_code.rest;

import edu.wgu.d387_sample_code.localization.WelcomeDisplayThread;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@RestController
public class WelcomeController {

    @GetMapping("/welcome")
    public List<String> getWelcomeMessages() {
        List<String> messages = new ArrayList<>();
        // Simulate threaded loading (run threads and collect results)
        WelcomeDisplayThread englishThread = new WelcomeDisplayThread(Locale.CANADA);
        WelcomeDisplayThread frenchThread = new WelcomeDisplayThread(Locale.CANADA_FRENCH);

        englishThread.start();
        frenchThread.start();

        try {
            englishThread.join();
            frenchThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Load messages directly for the response (avoid threading race conditions in API)
        messages.add(ResourceBundle.getBundle("translation", Locale.CANADA).getString("welcome"));
        messages.add(ResourceBundle.getBundle("translation", Locale.CANADA_FRENCH).getString("welcome"));

        return messages;
    }
}