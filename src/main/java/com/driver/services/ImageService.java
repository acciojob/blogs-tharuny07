package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
         Blog blog=blogRepository2.findById(blogId).get();

         Image image=new Image();
         image.setBlog(blog);
         image.setDescription(description);
         image.setDimensions(dimensions);

         List<Image> imageList=blog.getImageList();
         imageList.add(image);
         blogRepository2.save(blog);

         return image;
    }

    public void deleteImage(Integer id){
             imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        int count=0;
         Image image=imageRepository2.findById(id).get();
         String imageDimensions=image.getDimensions();

         String [] scnArray=screenDimensions.split("X");
         String [] imgArray=imageDimensions.split("X");

         int scn1=Integer.parseInt(scnArray[0]);
         int scn2=Integer.parseInt(scnArray[1]);
         int img1=Integer.parseInt(imgArray[0]);
         int img2=Integer.parseInt(imgArray[1]);

         return (scn1/img1)*(scn2/img2);
    }
}
