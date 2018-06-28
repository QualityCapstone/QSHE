package com.codeup.qshe.services.user;


import com.codeup.qshe.models.user.User;
import com.codeup.qshe.models.user.UserProfile;
import com.codeup.qshe.models.user.UserWithRoles;
import com.codeup.qshe.repositories.Roles;
import com.codeup.qshe.repositories.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service("customUserDetailsService")
public class UserDetailsLoader implements UserDetailsService {
    private final Users users;
    private final Roles roles;

    @Autowired
    public UserDetailsLoader(Users users, Roles roles) {
                this.users = users;
                this.roles = roles;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user = users.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("User could not be found for " + username);
        }

        return new UserWithRoles(user, roles.ofUserWith(username));

    }


    public UserProfile getProfile(Long id) {
        UserProfile profile = users.getUserProfile(id);
        return profile;
    }

    public UserProfile getProfile(String username) {
        User user = users.findByUsername(username);

        UserProfile profile = users.getUserProfile(user.getId());
        return profile;
    }



}
