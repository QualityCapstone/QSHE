package com.codeup.qshe.configuration;

import com.codeup.qshe.models.*;
import com.codeup.qshe.models.SiteSetting;
import com.codeup.qshe.models.State;
import com.codeup.qshe.models.StateCrime;
import com.codeup.qshe.models.StatePopulation;
import com.codeup.qshe.models.user.*;
import com.codeup.qshe.repositories.SiteSettings;
import com.codeup.qshe.services.CrimeService;
import com.codeup.qshe.services.PostService;
import com.codeup.qshe.services.StateService;
import com.codeup.qshe.services.StateUserRatingService;
import com.codeup.qshe.services.messages.MessagesService;
import com.codeup.qshe.services.user.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.github.javafaker.Faker;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class DataLoader implements ApplicationRunner {

    private SiteSettings site;
    private StateService stateDao;
    private UserService userDao;
    private StateUserRatingService ratingDao;
    private PasswordEncoder passwordEncoder;
    private MessagesService messageDao;
    private PostService postDao;


    @Value("${file-upload-path}")
    private String uploadPath;

    @Value("${avatar-img-location}")
    private String avatarPath;

    // will delete data and refresh test data
    // probably can move to app props or something...

    // SET TRUE: Resets the user data tables and create false data
    // Leave true, data now checks tables
    private static final boolean FRESHSTART = true;


    private Faker faker = new Faker();
    private Random rand = new Random();
    private List<String> avatars = new ArrayList<>();

    @Autowired
    public DataLoader(UserService userDao, SiteSettings site,
                      StateService stateDao,
                      PasswordEncoder passwordEncoder,
                      StateUserRatingService ratingDao,
                      MessagesService messageDao,
                      PostService postaDao


    ) {
        this.stateDao = stateDao;
        this.site = site;
        this.passwordEncoder = passwordEncoder;
        this.ratingDao = ratingDao;
        this.userDao = userDao;
        this.postDao = postaDao;
        this.messageDao = messageDao;
    }


    public void run(ApplicationArguments args) throws IOException, URISyntaxException, SQLException {

        if(FRESHSTART) {
            Random r = new Random();

            //How many users to create
            Integer usersToCreate = 150;
            // How many states a user will rate on average
            Integer statesRankedPerUser = 5;
            // Fake conversations seeding
            Integer maxConvosPerUser = 10;
            // Fake conversation length
            Integer convoMessages = 20;

            // State Post Topics
            Integer postTopicsPerState = 10;
            // State Response Per Topic
            Integer postResponsesPerTopic = 11;

            // Test Data for fake accounts
            String testUserName = "test";
            String testPassword = "test";

            // Creates record for Site settings
                try {
                    if (!site.isPopulated()) {
                        System.out.println("Populated Returned FALSE");
                    }
                } catch (NullPointerException e) {
                    SiteSetting setting = new SiteSetting(false);
                    site.save(setting);
                }


                // REFRESH APP DATA
                if(site.getFirst().getRefreshAppData() || !site.getFirst().getPopulated()) {
                    System.out.println("------- REFRESH APP DATA -----");
                    // STATE DATA, POPULATION DATA

                    avatars = uploadTestAvatars();

                    generateStaticData();

                    List<State> states = stateDao.getStates().findAll();


                    // START DATA GENERATION (USERS)
                    for (int i = 0; i < usersToCreate; i++) {
                        User user = createUser(testUserName + i, testPassword);

                        // State user Rankings
                        State state = stateDao.getStates().findByName(user.getProfile().getUserState());

                        generateStateUserMetrics(user, state);

                        for(int j = 1; j < statesRankedPerUser; j++) {
                            state = states.get(rand.nextInt(states.size()));
                            generateStateUserMetrics(user, state);
                        }

                    }


                    generateFakeConversations(maxConvosPerUser, convoMessages);
                    generateFakeTopicsAndResponses(postTopicsPerState,postResponsesPerTopic);

                }

                // REFRESH API DATA
                if(site.getFirst().getRefreshAPIs() || !site.getFirst().getPopulated()) {
                    System.out.println("------- REFRESH APIS -----");

                    // Get Population Data by State
                    for (int i = 1; i <= 9; i++) {
                        String popURL = "https://api.census.gov/data/2016/pep/population?get=POP,GEONAME,DATE_DESC&for=state:*&DATE=" + i +
                                "&key=50fdb2ea46d6471b7412b6b43204804309487999";
                        populationsByDate(popURL);

                    }

                    womenGradsByYear();
                    getPovertyData();
                    crimeData();

                }


            } //END FRESH START


            site.save(new SiteSetting(true));
    }



    // Function to kick off all static data generated as part of the application
    private void generateStaticData() {

        //Clean data
        messageDao.getMessages().deleteAll();
        stateDao.getPopulations().deleteAll();
        stateDao.getEducations().deleteAll();
        stateDao.getPoverties().deleteAll();
        stateDao.getCrimes().deleteAll();

        postDao.getPosts().deleteAll();
        postDao.getTopics().deleteAll();

        ratingDao.getUserRatings().deleteAll();

        userDao.getUsers().deleteAll();

        stateGenerator();
        metricGenerator();

    }


    private void metricGenerator() {


        List<StateMetric> metrics = new ArrayList<>();

        metrics.add(new StateMetric("Crime"));
        metrics.add(new StateMetric("Education"));
        metrics.add(new StateMetric("Employment"));
        metrics.add(new StateMetric("Health"));
        metrics.add(new StateMetric("Growth"));

        // Add State to DB

        this.ratingDao.getMetrics().deleteAll();
        this.ratingDao.getMetrics().saveAll(metrics);

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
        this.stateDao.getStaterepository().deleteAll();
        this.stateDao.getStaterepository().saveAll(states);
        System.out.println(stateDao.getStaterepository().findAll());


    }

    private void populationsByDate(String URL) throws URISyntaxException, IOException {

        URL jsonURL = new URL(URL);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonURL);


        List<StatePopulation> populationList = new ArrayList<>();

        //ignore first node
        int j = 0;
        for (JsonNode n : node) {

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

                if (i == 0) {
                    population = Long.parseLong(l);
                }

                if (i == 1) {
                    state = stateDao.getStaterepository().findByName(l);
                }
                if (i == 2) {
                    String[] split = l.split("\\s+");
                    dateCreated = split[0];
                }
                i++;
            }

            // Convert date from String to localdate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
            LocalDate dateFormatted = LocalDate.parse(dateCreated, formatter);

            StatePopulation data = new StatePopulation(state, population, dateFormatted);

            populationList.add(data);

        }

        // Save all populations
        stateDao.getPopulations().saveAll(populationList);
    }

    private HashMap<String, String> getStateData() throws IOException {

        HashMap<String, String> data = new HashMap<>();

        String stateDataURL = "https://api.datausa.io/attrs/geo/?sumlevel=040";


        URL jsonURL = new URL(stateDataURL);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonURL);


        //ignore first node
        int i = 0;
        for(JsonNode node1 :  node.findValues("data")) {
            for(JsonNode n : node1) {

                List<String> list = mapper.readValue(n.toString(), TypeFactory.defaultInstance().constructCollectionType(List.class, String.class));

                int j = 0;
                String stateName = "";
                String stateId = "";
                String img = "";
                for (String l : list) {

                    if(j==1) { stateName = l;}
                    if(j==3) { img = l; }
                    if(j==8) { stateId = l; }
                    j++;
                }


                // Adds state image
               State state = stateDao.getStates().findByName(stateName);

                if(state != null) {
                    state.setImg(img);
                    stateDao.getStates().save(state);
                }


                data.put(stateId, stateName);
            }
        }

        return data;

    }

    // Saves women grads by state.
    private void womenGradsByYear() throws IOException {

        HashMap<String, String> state = getStateData();

        List<StateEducation> education = new ArrayList<>();

       // https://api.datausa.io/api/?show=geo&sumlevel=state&required=year,poverty_female,poverty_male,num_emp_male,num_emp_female,teen_births,mammography_screening,mean_commute_minutes

        String dataURL = "https://api.datausa.io/api/?show=geo&sumlevel=state&required=grads_women,year";

        URL jsonURL = new URL(dataURL);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonURL);

        //ignore first node
        int i = 0;
        for(JsonNode node1 :  node.findValues("data")) {
            for (JsonNode n : node1) {
                System.out.println(n.toString());


                List<String> list = mapper.readValue(n.toString(), TypeFactory.defaultInstance().constructCollectionType(List.class, String.class));

                int j = 0;
                String stateId = "";
                String count = "";
                String year = "";
                for (String l : list) {

                    if(j==0) { stateId = l; }
                    if(j==1) { count = l;   }
                    if(j==2) { year = l;    }

                    j++;

                }


               String stateName = state.get(stateId);
                State dbState = stateDao.getStaterepository().findByName(stateName);
                education.add(new StateEducation(dbState,Long.parseLong(count),Integer.parseInt(year),stateId));

            }
        }

        stateDao.getEducations().saveAll(education);
    }

    // Saves women grads by state.
    private void getPovertyData() throws IOException {

        HashMap<String, String> state = getStateData();

        List<StatePoverty> poverty = new ArrayList<>();

        String dataURL = "https://api.datausa.io/api/?show=geo&sumlevel=state&required=year,poverty_female,poverty_male";

        URL jsonURL = new URL(dataURL);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonURL);

        //ignore first node
        int i = 0;
        for(JsonNode node1 :  node.findValues("data")) {
            for (JsonNode n : node1) {
                System.out.println(n.toString());

                List<String> list = mapper.readValue(n.toString(), TypeFactory.defaultInstance().constructCollectionType(List.class, String.class));

                int j = 0;
                String stateId = "";
                String year = "";
                String femalePoverty = "";
                String malePoverty = "";

                for (String l : list) {

                    if(j==0) { stateId = l; }
                    if(j==1) { year = l;   }
                    if(j==2) { femalePoverty = l;    }
                    if(j==3) { malePoverty = l;    }

                    j++;

                }

                String stateName = state.get(stateId);
                State dbState = stateDao.getStaterepository().findByName(stateName);

                poverty.add(new StatePoverty(Integer.parseInt(year),dbState,
                        stateId, Double.parseDouble(femalePoverty), Double.parseDouble(malePoverty)));

            }
        }

        stateDao.getPoverties().saveAll(poverty);
    }

    private void crimeData() throws IOException {

      List<State> states  = stateDao.getStates().findAll();

      for(State state : states) {
          String crimeURL = "https://api.usa.gov/crime/fbi/sapi/api/estimates/states/"+state.getAbbr()+"?api_key=iiHnOKfno2Mgkt5AynpvPpUQTEyxE77jo1RU8PIv";
          stateCrimesByYear(crimeURL);

      }

    }

    private void stateCrimesByYear(String url) throws IOException {
        URL json = new URL(url);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(json);

        List<StateCrime> crimeList = new ArrayList<>();

        for (int i = 17; i < 22; i++) {
//            reach inside of JSON and ignore first node
            JsonNode inner = node.get("results").get(i);
            StateCrime data = new StateCrime(
                    stateDao.getStates().findByAbbr(inner.get("state_abbr").asText()),
                    inner.get("state_abbr").asText(),
                    inner.get("population").asLong(),
                    inner.get("year").asLong(),
                    inner.get("violent_crime").asLong(),
                    inner.get("homicide").asLong(),
                    inner.get("rape_legacy").asLong(),
                    inner.get("robbery").asLong(),
                    inner.get("aggravated_assault").asLong(),
                    inner.get("property_crime").asLong(),
                    inner.get("burglary").asLong(),
                    inner.get("larceny").asLong(),
                    inner.get("motor_vehicle_theft").asLong(),
                    inner.get("arson").asLong()
            );
            crimeList.add(data);
        }
//        save all crimes
        stateDao.getCrimes().saveAll(crimeList);
    }

    private User createUser(String username, String password) {

        String avatar =  avatars.get(rand.nextInt(avatars.size()));

        User user = new User(username, password, LocalDateTime.now(), LocalDateTime.now());
        String hash = passwordEncoder.encode(password);
        user.setPassword(hash);
        user.setCreatedAt(LocalDateTime.now());

        user.setProfile(new UserProfile(faker.name().fullName(),  faker.name().firstName(),
                faker.name().lastName(),  faker.internet().emailAddress(),  username,  faker.address().state()));

        user.getProfile().setUploadPath(avatar);

        user = userDao.getUsers().save(user);
        userDao.getUsers().addDefaultRole(user.getId());

        return user;
    }


    private List<String> uploadTestAvatars() throws IOException {

        List<String> data = new ArrayList<>();

        for (int i = 1; i <= 13; i++) {

            try {

                ClassPathResource classPathResource = new ClassPathResource("static/img/avatars/" + i +".png");
                File file= classPathResource.getFile();

                System.out.println( i + " :  moving avatars");
                //     File file = new File(avatarPath + "/" + i + ".png");

               // String filename = UUID.randomUUID().toString() + ".png";
                //  String filepath = Paths.get(uploadPath, filename).toString();

            Files.copy(file.toPath(), (new File(uploadPath + "/" + file.getName())).toPath(),
                    StandardCopyOption.REPLACE_EXISTING);

            data.add(file.getName());

            } catch (NullPointerException e) {
                e.printStackTrace();
            }



        }
        return data;
    }


    private void generateStateUserMetrics(User user, State state) {

        List<StateMetric> metrics = ratingDao.getMetrics().findAll();

        for(StateMetric metric : metrics) {

            float rating = (float) faker.random().nextInt(1,10);

            ratingDao.getUserRatings().save(new StateUserRating(state, user, metric, rating));
        }

    }

    private void generateFakeConversations(Integer maxConvosPerUser, Integer convoMessages) {

        List<User> users = userDao.getUsers().findAll();
        List<Message> messages = new ArrayList<>();

        for(User sender : users ) {

            for(int j = 0; j < maxConvosPerUser; j++) {

                User recipient = users.get(rand.nextInt(users.size()));

                for(int i = 0; i < convoMessages; i++) {
                    String message = faker.friends().quote();
                    if(i%2 ==  0) {
                        messages.add(new Message(sender, recipient, message));
                    } else {
                        messages.add(new Message(recipient, sender, message));
                    }
                }

            }
        }

        messageDao.getMessages().saveAll(messages);

    }

    private void generateFakeTopicsAndResponses(Integer topicPerState, Integer responsesPerTopic) {

        List<PostTopic> topics = new ArrayList<>();
        List<State> states = stateDao.getStates().findAll();
        List<User> users = userDao.getUsers().findAll();

        List<Post> posts = new ArrayList<>();

        for(State state  : states) {
            // Creates Topics
            for (int i = 0; i < topicPerState; i++) {
                User user = users.get(rand.nextInt(users.size()));
                PostTopic topic = new PostTopic(user, faker.book().title(), state, faker.book().title());
                topics.add(topic);
            }
        }

        postDao.getTopics().saveAll(topics);

            // Create follow up posts
            for(PostTopic topic : postDao.getTopics().findAll()) {
                // Creates Topics

                for(int i = 0; i < responsesPerTopic; i++) {
                    User user = users.get(rand.nextInt(users.size()));
                    Post post = new Post(topic,user,faker.shakespeare().romeoAndJulietQuote());


                    posts.add(post);
                }

            }

            postDao.getPosts().saveAll(posts);


    }

}

