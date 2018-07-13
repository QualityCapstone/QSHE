package com.codeup.qshe.services;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.PhotosInterface;
import com.flickr4java.flickr.photos.SearchParameters;
import com.flickr4java.flickr.test.TestInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Collections;


public class FlickrService {

    private Flickr f;

    public FlickrService(String apiKey, String sharedSecret) {
         this.f = new Flickr(apiKey, sharedSecret, new REST());
         TestInterface testInterface = f.getTestInterface();
    }


    // TODO: redo this....better
    public PhotoList<Photo> getPhotos(String search, Integer limit) throws FlickrException {

        PhotosInterface photoInterface = f.getPhotosInterface();

        SearchParameters searchParams = new SearchParameters();
        searchParams.setText(search + " State scenery");

        PhotoList<Photo> photos = photoInterface.search(searchParams, limit, 1);

        return photos;

    }


    public Photo getPhoto(String search) throws FlickrException {
       return getPhotos(search, 1).get(0);
    }


}
