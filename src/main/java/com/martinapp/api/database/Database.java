package com.martinapp.api.database;

import com.martinapp.api.common.Address;
import com.martinapp.api.entity.Customer;
import com.martinapp.api.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    @Bean
    public CommandLineRunner init(CustomerRepository repository) {
        return args -> {
            logger.info("Preloading " + repository.save(new Customer("Jijon", "Bdn", new Date(System.currentTimeMillis()), new Address("1 Rue du Code", "Paris", "France", "75000"), "0645016801")));
        };
    }
}
