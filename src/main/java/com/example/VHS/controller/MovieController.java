package com.example.VHS.controller;

import com.example.VHS.entity.Vhs;
import com.example.VHS.exception.VhsException;
import com.example.VHS.service.MovieDbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
    @Autowired
    private MovieDbService movieDbService;

    @GetMapping("/authenticate")
    public String authenticate(){
        try {
           return movieDbService.authenticate();

        }catch (IOException e){
            String error = "Error occurred:" + e.getMessage();
            logger.error(error);
            return error;
        }
    }

    @GetMapping("/popular")
    public String popular(){
        try {
            return movieDbService.getPopular();

        }catch (IOException e){
            String error = "Error occurred:" + e.getMessage();
            logger.error(error);
            return error;
        }
    }

    @GetMapping("/popularWeHave")
    public Map<String, String> popularWeHave(){
        try {
            return movieDbService.getPopularWeHave();

        }catch (IOException e){
            String error = "Error occurred:" + e.getMessage();
            logger.error(error);
            throw new VhsException(e.getMessage());
        }
    }
}
