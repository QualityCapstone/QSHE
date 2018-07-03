package com.codeup.qshe.services.user;

import com.codeup.qshe.models.user.ExtendedSocialUser;
import com.codeup.qshe.models.user.User;
import com.codeup.qshe.models.user.UserProfile;
import com.codeup.qshe.repositories.Users;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserService {
    private Users users;

    @Autowired
    public UserService(Users users) {
        this.users = users;
    }

    public Users getUsers() {
        return users;
    }

    public void createUser(String username, UserProfile profile) {
        users.addUser(username, RandomStringUtils.randomAlphanumeric(8));
        // get user ID
        User user = users.findByUsername(username);
        users.addDefaultRole(user.getId());
        users.addProfile(profile.getEmail(),
                profile.getFirstName(),
                profile.getLastName(),
                profile.getName(),
                profile.getUsername());

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
