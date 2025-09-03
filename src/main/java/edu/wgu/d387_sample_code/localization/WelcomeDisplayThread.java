package edu.wgu.d387_sample_code.localization;

import java.util.Locale;
import java.util.ResourceBundle;

public class WelcomeDisplayThread extends Thread {
    private final Locale locale;

    public WelcomeDisplayThread(Locale locale) {
        this.locale = locale;
    }

    @Override
    public void run() {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("translation", locale);
            String message = bundle.getString("welcome");
            System.out.println("Thread: " + Thread.currentThread().getName() + " | Locale: " + locale + " | Welcome: " + message);
        } catch (Exception e) {
            System.err.println("Error in thread " + Thread.currentThread().getName() + ": " + e.getMessage());
        }
    }
}