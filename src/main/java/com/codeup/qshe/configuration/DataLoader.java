package com.codeup.qshe.configuration;


import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class DataLoader implements ApplicationRunner {

    // will delete data and refresh test data
    // probably can move to app props or something...

    // SET TRUE: Resets the user data tables and create false data
    // THIS DOES NOT CHANGE THE BASE TABLES
    private static final boolean FRESHSTART = false;

    // THIS CLEARS STUFF! EVERYTHING REFRESH!
    private static final boolean REFRESHBASE = false;


    private Faker faker = new Faker();
    private Random rand = new Random();


    @Autowired
    public DataLoader() {


    }


    public void run(ApplicationArguments args) {
        if (FRESHSTART) {
            Random r = new Random();

        } //END FRESH START
    }


}
