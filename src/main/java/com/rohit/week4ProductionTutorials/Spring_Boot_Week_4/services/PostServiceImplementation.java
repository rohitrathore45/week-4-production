package com.rohit.week4ProductionTutorials.Spring_Boot_Week_4.services;

import com.rohit.week4ProductionTutorials.Spring_Boot_Week_4.dto.PostDTO;
import com.rohit.week4ProductionTutorials.Spring_Boot_Week_4.entities.PostEntity;
import com.rohit.week4ProductionTutorials.Spring_Boot_Week_4.exceptions.ResourceNotFoundException;
import com.rohit.week4ProductionTutorials.Spring_Boot_Week_4.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PostServiceImplementation implements PostService{

    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<PostDTO> getAllPost() {
        return postRepository.
                findAll().
                stream().
                map(postEntity -> modelMapper.map(postEntity, PostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO createNewPost(PostDTO inputPost) {
        PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);
        return modelMapper.map(postRepository.save(postEntity), PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Long postId) {
        PostEntity postEntity = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found " + postId));
        return modelMapper.map(postEntity, PostDTO.class);

    }

    @Override
    public PostDTO updatePost(PostDTO inputPost, Long postId) {
        PostEntity olderPost = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found " + postId));
        inputPost.setId(postId);
        modelMapper.map(inputPost, olderPost);
        PostEntity savePostEntity = postRepository.save(olderPost);
        return modelMapper.map(savePostEntity, PostDTO.class);
    }
}
