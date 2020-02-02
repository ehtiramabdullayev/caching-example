package com.caching.proj.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/all")
    public List<User> getAllUsers(){
        return userService.findAll();
    }
    @PutMapping(value = "/user")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }
}
