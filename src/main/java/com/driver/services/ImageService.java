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
         int midIndexOfScreen=screenDimensions.indexOf('X');
         int midIndexOfImage=imageDimensions.indexOf('X');

         int imageArea=Integer.parseInt(imageDimensions.substring(0,midIndexOfImage))*Integer.parseInt(imageDimensions.substring(midIndexOfImage+1));
         int screenArea=Integer.parseInt(screenDimensions.substring(0,midIndexOfScreen))*Integer.parseInt(screenDimensions.substring(midIndexOfScreen+1));
         int n=imageArea;
         while(n<=screenArea)
         {
             count++;
             n+=imageArea;
         }
        System.out.println(imageArea+""+screenArea);
         return count;
    }
}
