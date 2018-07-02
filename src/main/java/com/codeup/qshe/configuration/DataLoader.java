package com.codeup.qshe.configuration;


import com.codeup.qshe.models.SiteSetting;
import com.codeup.qshe.models.State;
import com.codeup.qshe.models.StatePopulation;
import com.codeup.qshe.repositories.SiteSettings;
import com.codeup.qshe.services.StateService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.net.URL;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataLoader implements ApplicationRunner {

    private SiteSettings site;
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
    public DataLoader(SiteSettings site , StateService stateDao) {
        this.stateDao = stateDao;
        this.site = site;

    }

    public void run(ApplicationArguments args) throws IOException, URISyntaxException {


    try {
        if (!site.isPopulated()) {
            System.out.println("Populated Returned FALSE");
            SiteSetting setting = site.getFirst();
        }
    } catch(NullPointerException e) {
        SiteSetting setting = new SiteSetting(false);
        site.save(setting);
    }

        if (!site.getFirst().getPopulated()) {
            Random r = new Random();

            generateStaticData();

            // Get Population Data by State
            for(int i = 1; i <= 9; i++) {
                String popURL = "https://api.census.gov/data/2016/pep/population?get=POP,GEONAME,DATE_DESC&for=state:*&DATE=" + i;
                populationsByDate(popURL);
            }

        } //END FRESH START

        site.save(new SiteSetting(true));
    }


    // Function to kick off all static data generated as part of the application
    private void generateStaticData() {
        //Clean data
        stateDao.getPopulations().deleteAll();

        stateGenerator();

    }

    private void stateGenerator() {

        List<State> states = new ArrayList<>();

        states.add(new State("AL", "Alabama"));
        states.add(new State("AK", "Alaska"));
        states.add(new State("AZ", "Arizona"));
        states.add(new State("AR", "Arkansas"));
        states.add(new State("CA", "California"));
        states.add(new State("CT", "Connecticut"));
        states.add(new State("CO", "Colorado"));
        states.add(new State("DE", "Delaware"));
        states.add(new State("DC", "District of Columbia"));
        states.add(new State("FL", "Florida"));
        states.add(new State("GA", "Georgia"));
        states.add(new State("HI", "Hawaii"));
        states.add(new State("ID", "Idaho"));
        states.add(new State("IL", "Illinois"));
        states.add(new State("IN", "Indiana"));
        states.add(new State("IA", "Iowa"));
        states.add(new State("KS", "Kansas"));
        states.add(new State("KY", "Kentucky"));
        states.add(new State("LA", "Louisiana"));
        states.add(new State("ME", "Maine"));
        states.add(new State("MD", "Maryland"));
        states.add(new State("MA", "Massachusetts"));
        states.add(new State("MI", "Michigan"));
        states.add(new State("MN", "Minnesota"));
        states.add(new State("MS", "Mississippi"));
        states.add(new State("MO", "Missouri"));
        states.add(new State("MT", "Montana"));
        states.add(new State("NE", "Nebraska"));
        states.add(new State("NV", "Nevada"));
        states.add(new State("NH", "New Hampshire"));
        states.add(new State("NJ", "New Jersey"));
        states.add(new State("NM", "New Mexico"));
        states.add(new State("NY", "New York"));
        states.add(new State("NC", "North Carolina"));
        states.add(new State("ND", "North Dakota"));
        states.add(new State("OH", "Ohio"));
        states.add(new State("OK", "Oklahoma"));
        states.add(new State("OR", "Oregon"));
        states.add(new State("PA", "Pennsylvania"));
        states.add(new State("RI", "Rhode Island"));
        states.add(new State("SC", "South Carolina"));
        states.add(new State("SD", "South Dakota"));
        states.add(new State("UT", "Utah"));
        states.add(new State("VT", "Vermont"));
        states.add(new State("VA", "Virginia"));
        states.add(new State("WA", "Washington"));
        states.add(new State("WV", "West Virginia"));
        states.add(new State("TN", "Tennessee"));
        states.add(new State("TX", "Texas"));
        states.add(new State("WI", "Wisconsin"));
        states.add(new State("WY", "Wyoming"));

        // Add State to DB
        this.stateDao.getStates().deleteAll();
        this.stateDao.getStates().saveAll(states);



    }


    private void populationsByDate(String URL) throws URISyntaxException, IOException {

        URL jsonURL = new URL(URL);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonURL);


        List<StatePopulation> populationList = new ArrayList<>();

        //ignore first node
        int j = 0;
        for(JsonNode n : node) {

            if (j == 0) {
                j++;
                continue;
            }

            List<String> list = mapper.readValue(n.toString(), TypeFactory.defaultInstance().constructCollectionType(List.class, String.class));

            int i = 0;
            Long population = 0L;
            State state = new State();
            String dateCreated = "";

            for (String l : list) {

                if(i == 0) {
                    population = Long.parseLong(l);
                }

                if(i == 1) {
                    state = stateDao.getStates().findByName(l);
                }
                if(i == 2) {
                    String[] split = l.split("\\s+");
                    dateCreated = split[0];
                }
                i++;
            }

            // Convert date from String to localdate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
            LocalDate dateFormatted = LocalDate.parse(dateCreated, formatter);

                StatePopulation data = new StatePopulation(state, population, dateFormatted );

            populationList.add(data);

        }

        // Save all populations
        stateDao.getPopulations().saveAll(populationList);
    }


}
