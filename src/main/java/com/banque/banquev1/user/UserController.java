package com.banque.banquev1.user;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private static final List<User> USERS = Arrays.asList(
            new User(1,"Mourad","Abbay"),
            new User(2,"Yassir","Adham"),
            new User(3,"Salah","Jaafari")
    );

    @GetMapping(path = "{ID}")
    public User getUser(@PathVariable("ID")  Integer ID){
        return USERS.stream()
                .filter(user -> ID.equals(user.getID()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User" + ID + " does not exists"));

    }
}
