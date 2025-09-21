package org.sampong.onlinebanking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableRetry
@EnableScheduling
public class OnlineBankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineBankingApplication.class, args);
    }

}
