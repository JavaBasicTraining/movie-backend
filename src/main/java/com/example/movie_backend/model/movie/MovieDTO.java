package com.example.movie_backend.model.movie;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class MovieDTO {
    
    private UUID id;
    
    private String name;
    
    private String posterUrl;
    
    private String viTitle;
    
    private String enTitle;
    
    private String description;
}
