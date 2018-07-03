package com.codeup.qshe.controller;

import com.codeup.qshe.models.user.User;
import com.codeup.qshe.models.user.UserProfile;
import com.codeup.qshe.models.user.UserWithRoles;
import com.codeup.qshe.repositories.Roles;
import com.codeup.qshe.repositories.Users;
import com.codeup.qshe.services.user.SimpleSocialUsersDetailService;
import com.codeup.qshe.services.user.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
public class UserController {
    private Users users;
    private PasswordEncoder passwordEncoder;
    private Roles roles;


    public UserController(Users users, PasswordEncoder passwordEncoder, Roles roles) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.roles = roles;

    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "users/sign-up";

    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user, @ModelAttribute UserProfile profile) {

        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setCreatedAt(LocalDateTime.now());

        users.save(user);
        users.addDefaultRole(user.getId());

        authenticate(user);
        return "redirect:/dashboard";
    }



    @GetMapping("/profile")
    public String loadProfile(Model model) {

        return "users/profile";
    }





    private void authenticate(User user) {
        UserDetails userDetails = new UserWithRoles(user, roles.ofUserWith(user.getUsername()));
        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(auth);
    }



}
