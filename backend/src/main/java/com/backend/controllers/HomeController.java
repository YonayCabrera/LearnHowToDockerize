package com.backend.controllers;

import com.backend.actions.GetAllUsers;
import com.backend.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {
    private final GetAllUsers getAllUsers;

    @Autowired
    public HomeController(GetAllUsers getAllUsers){
        this.getAllUsers = getAllUsers;
    }

    @GetMapping("/")
    public List<User> getUsers(){
        return getAllUsers.execute();
    }
}
