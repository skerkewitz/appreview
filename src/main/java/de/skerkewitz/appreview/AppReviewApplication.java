package de.skerkewitz.appreview;

import de.skerkewitz.appreview.service.ReviewEntry;
import de.skerkewitz.appreview.service.ReviewService;
import de.skerkewitz.appreview.service.db.ReviewEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.List;


//@Import({
//        AopAutoConfiguration.class,
//        DataSourceAutoConfiguration.class,
//        HibernateJpaAutoConfiguration.class,
//        JmxAutoConfiguration.class,
//        JpaBaseConfiguration.class,
//        JpaRepositoriesAutoConfiguration.class,
//        JtaAutoConfiguration.class,
//        PersistenceExceptionTranslationAutoConfiguration.class,
//        PropertyPlaceholderAutoConfiguration.class,
//        TransactionAutoConfiguration.class
//})

//@EnableConfigurationProperties
//@EnableSpringConfigured
//@Enable
//@EnableAutoConfiguration
@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
public class AppReviewApplication implements CommandLineRunner {

    // --appStoreAppId="Spring"
    @Value("${appStoreAppId}")
    private Integer appStoreAppId;

    @Autowired
    private ReviewEntryRepository reviewEntryRepository;

    @Autowired
    private ReviewService reviewService;

    public static void main(String[] args) {
        SpringApplication.run(AppReviewApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        List<ReviewEntry> reviewEntries = reviewService.fetchNewEntries(appStoreAppId);
        System.out.println("Found " + reviewEntries.size() + " Elements");
        for (ReviewEntry reviewEntry : reviewEntries) {
            System.out.println(" -- " + reviewEntry);
        }
    }
}
