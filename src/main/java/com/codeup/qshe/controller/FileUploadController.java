package com.codeup.qshe.controller;

import com.codeup.qshe.models.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@Controller
public class FileUploadController {

    @Value("${file-upload-path}")
    private String uploadPath;

    @GetMapping("/fileupload")
    public String showUploadFileForm() {
        return "redirect:users/profile";
    }

    @PostMapping("/fileupload")
    public String saveFile(
            @RequestParam(name = "file")MultipartFile uploadedFile,
            Model model,
            @ModelAttribute User user
            ) {
//        if (uploadedFile.isEmpty()) {
//            model.addAttribute("message", "Please choose a file to upload.");
//            return "redirect:fileupload";
//        }
        String filename = uploadedFile.getOriginalFilename();
        String filepath = Paths.get(uploadPath, filename).toString();
        File destinationFile = new File(filepath);
        user.setUploadPath(filepath);
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