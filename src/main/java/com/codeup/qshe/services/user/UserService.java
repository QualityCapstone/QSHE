package com.codeup.qshe.services.user;

import com.codeup.qshe.models.user.*;
import com.codeup.qshe.repositories.UserConnections;
import com.codeup.qshe.repositories.Users;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public class UserService {
    private Users users;
    private UserConnections connections;

    @Value("${file-upload-path}")
    private String uploadPath;



    @Autowired
    public UserService(Users users, UserConnections  connections) {
        this.users = users;
        this.connections = connections;
    }

    public Users getUsers() {
        return users;
    }

    public void createUser(String username, UserProfile profile) throws IOException {

        User user = new User(username, RandomStringUtils.randomAlphanumeric(8));

        //TODO: Change reigster process to get state data.
        profile.setUserState("Texas");
        profile.setEmail("update@me.now");


        user.setProfile(profile);

        users.addDefaultRole(user.getId());
        users.save(user);


    }

    public List<User> findAll() {
        Iterable<User> users = getUsers().findAll();
        return (List<User>) users;
    }

    public User findOne(long id){
        User user = users.findById(id).get();
        return user;
    }


    public User getLoggedInUser() {

        if(SecurityContextHolder.getContext().getAuthentication() != null) {
            try {
                return  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            } catch (ClassCastException e) {
                try {
                    System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
                    ExtendedSocialUser socialUser = (ExtendedSocialUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    return getUsers().findByUsername(socialUser.getUserId());
                } catch (NullPointerException x) {
                   return null;
                }
            }
        }
        return null;

    }


}
