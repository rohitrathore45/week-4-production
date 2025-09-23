package com.rohit.week4ProductionTutorials.Spring_Boot_Week_4.controllers;

import com.rohit.week4ProductionTutorials.Spring_Boot_Week_4.dto.PostDTO;
import com.rohit.week4ProductionTutorials.Spring_Boot_Week_4.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDTO> getAllPost(){
        return postService.getAllPost();
    }

    @PostMapping
    public PostDTO createNewPost(@RequestBody PostDTO inputPost){
        return postService.createNewPost(inputPost);
    }

    @GetMapping("/{postId}")
    public PostDTO getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }
}
