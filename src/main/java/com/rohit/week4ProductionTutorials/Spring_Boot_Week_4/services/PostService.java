package com.rohit.week4ProductionTutorials.Spring_Boot_Week_4.services;


import com.rohit.week4ProductionTutorials.Spring_Boot_Week_4.dto.PostDTO;

import java.util.List;

public interface PostService {

    List<PostDTO> getAllPost();

    PostDTO createNewPost(PostDTO inputPost);

    PostDTO getPostById(Long postId);
}
