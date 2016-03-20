package de.skerkewitz.appreview;

import de.skerkewitz.appreview.service.ReviewEntry;
import de.skerkewitz.appreview.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class AppReviewApplication implements CommandLineRunner {

    // --appStoreAppId="Spring"
    @Value("${appStoreAppId}")
    private Integer appStoreAppId;

    @Autowired
    private ReviewService reviewService;

    public static void main(String[] args) {
        SpringApplication.run(AppReviewApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        List<ReviewEntry> reviewEntries = reviewService.fetchEntries(appStoreAppId);
        System.out.println("Found " + reviewEntries.size() + " Elements");
        for (ReviewEntry reviewEntry : reviewEntries) {
            System.out.println(" -- " + reviewEntry);
        }
    }
}
