package com.codeup.qshe.controller;


import com.codeup.qshe.services.FlickrService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

@Controller
public class ImageController {


    @Value("${flickr-key}")
    private String apiKey;
    @Value("${flickr-secret}")
    private String sharedSecret;




    @GetMapping("img/{state}/{order}")
    public @ResponseBody byte[] getImage(@PathVariable("state") String state, @PathVariable("order") Integer order) {

        FlickrService f = new FlickrService(apiKey, sharedSecret);

//        f.getPhotos();
//
//
//        InputStream in = new ByteArrayInputStream(att.getAttachmentFile());
//        BufferedImage img = ImageIO.read(in);
//        ByteArrayOutputStream bao = new ByteArrayOutputStream();
//        ImageIO.write(img, "jpg", bao);
//        return bao.toByteArray();
        return null;


    }

}
