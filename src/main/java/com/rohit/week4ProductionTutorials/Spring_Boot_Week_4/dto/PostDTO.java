package com.rohit.week4ProductionTutorials.Spring_Boot_Week_4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Long id;

    private String title;

    private String description;

}
