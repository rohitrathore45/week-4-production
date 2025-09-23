package com.rohit.week4ProductionTutorials.Spring_Boot_Week_4.repositories;

import com.rohit.week4ProductionTutorials.Spring_Boot_Week_4.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
