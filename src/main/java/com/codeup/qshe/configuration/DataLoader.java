package com.codeup.qshe.configuration;


import com.codeup.qshe.models.State;
import com.codeup.qshe.services.StateService;
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


    private StateService stateDao;


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
    public DataLoader(StateService stateDao) {
        this.stateDao = stateDao;

    }

    public void run(ApplicationArguments args) {
        if (FRESHSTART) {
            Random r = new Random();

            generateStaticData();


            // Get Population Data by State
            String url= "https://api.census.gov/data/2016/pep/population?get=POP,GEONAME&for=state:*&DATE=9";

        } //END FRESH START
    }




    // Function to kick off all static data generated as part of the application
    private void generateStaticData() {
        stateGenerator();
    }

    private void stateGenerator() {

        List<State> states = new ArrayList<>();

        states.add(new State("AL", "Alabama");
        states.add(new State("AK", "Alaska");
        states.add(new State("AZ", "Arizona");
        states.add(new State("AR", "Arkansas");
        states.add(new State("CA", "California");
        states.add(new State("CT", "Connecticut");
        states.add(new State("CO", "Colorado");
        states.add(new State("DE", "Delaware");
        states.add(new State("DC", "District of Columbia");
        states.add(new State("FL", "Florida");
        states.add(new State("GA", "Georgia");
        states.add(new State("HI", "Hawaii");
        states.add(new State("ID", "Idaho");
        states.add(new State("IL", "Illinois");
        states.add(new State("IN", "Indiana");
        states.add(new State("IA", "Iowa");
        states.add(new State("KS", "Kansas");
        states.add(new State("KY", "Kentucky");
        states.add(new State("LA", "Louisiana");
        states.add(new State("ME", "Maine");
        states.add(new State("MD", "Maryland");
        states.add(new State("MA", "Massachusetts");
        states.add(new State("MI", "Michigan");
        states.add(new State("MN", "Minnesota");
        states.add(new State("MS", "Mississippi");
        states.add(new State("MO", "Missouri");
        states.add(new State("MT", "Montana");
        states.add(new State("NE", "Nebraska");
        states.add(new State("NV", "Nevada");
        states.add(new State("NH", "New Hampshire");
        states.add(new State("NJ", "New Jersey");
        states.add(new State("NM", "New Mexico");
        states.add(new State("NY", "New York");
        states.add(new State("NC", "North Carolina");
        states.add(new State("ND", "North Dakota");
        states.add(new State("OH", "Ohio");
        states.add(new State("OK", "Oklahoma");
        states.add(new State("OR", "Oregon");
        states.add(new State("PA", "Pennsylvania");
        states.add(new State("RI", "Rhode Island");
        states.add(new State("SC", "South Carolina");
        states.add(new State("SD", "South Dakota");
        states.add(new State("UT", "Utah");
        states.add(new State("VT", "Vermont");
        states.add(new State("VA", "Virginia");
        states.add(new State("WA", "Washington");
        states.add(new State("WV", "West Virginia");
        states.add(new State("TN", "Tennessee");
        states.add(new State("TX", "Texas");
        states.add(new State("WI", "Wisconsin");
        states.add(new State("WY", "Wyoming");

        // Add State to DB
        this.stateDao.getStates().deleteAll();
        this.stateDao.getStates().saveAll(states);



    }


}
