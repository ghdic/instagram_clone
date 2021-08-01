package com.instagram.Service;

import com.instagram.Repository.PostRepo;
import com.instagram.model.Post;
import com.instagram.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserService userService;

    public Post submitPostToDataBase(Post post) {
        return postRepo.save(post);
    }

    public ArrayList<Post> retrivePostFromDB() {
        ArrayList<Post> postArrayList = postRepo.findAll();
        for(int i = 0; i < postArrayList.size(); i++) {
            Post post = postArrayList.get(i);
            User user = userService.displayUserMetaData(post.getUserId());
            if(user != null)
                post.setUserName(user.getUserName());
        }
        return postArrayList;
    }
}
