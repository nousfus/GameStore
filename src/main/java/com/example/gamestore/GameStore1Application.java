package com.example.gamestore;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.gamestore.dao.UserRolesDao;
import com.example.gamestore.entity.UserRoles;

@SpringBootApplication
public class GameStore1Application {

    public static void main(String[] args) {
       SpringApplication.run(GameStore1Application.class, args);
        
    }
}