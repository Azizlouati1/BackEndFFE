package com.cni.elearning.Controllers.Users;

import com.cni.elearning.Models.Users.User;
import com.cni.elearning.Services.Users.IUserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final IUserService userService;

    public UserController (IUserService userService) {
        this.userService = userService;
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }
    @GetMapping("/")
    public List<User> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return users;
    }
    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        userService.deleteUser(id);
    }
    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable int id){
        return userService.updateUser(user,id);
    }

}
