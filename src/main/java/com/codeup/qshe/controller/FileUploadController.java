package com.codeup.qshe.controller;

import com.codeup.qshe.models.user.User;
import com.codeup.qshe.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class FileUploadController {
    UserService userDao;


    @Value("${file-upload-path}")
    private String uploadPath;

    @Autowired
    public FileUploadController(UserService userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/fileupload")
    public String showUploadFileForm() {
        return "redirect:users/profile";
    }




    @PostMapping("/fileupload")
    public String saveFile(
            @RequestParam(name = "file")MultipartFile uploadedFile,
            Model model) {


        User user = userDao.getLoggedInUser();
        User copy = new User(user);


        String filename = uploadedFile.getOriginalFilename().;
//        String filename = UUID.randomUUID().toString();
        String filepath = Paths.get(uploadPath, filename).toString();
        File destinationFile = new File(filepath);
        copy.setUploadPath(filename);

        userDao.getUsers().save(copy);
        try {
            uploadedFile.transferTo(destinationFile);
            model.addAttribute("message", "File successfully uploaded!");
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Oops! Something went wrong..");
        }
        return "redirect:users/profile";
    }
}