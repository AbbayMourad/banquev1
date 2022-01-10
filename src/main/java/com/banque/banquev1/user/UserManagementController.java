package com.banque.banquev1.user;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v2/users")
public class UserManagementController {

    private static final List<User> USERS = Arrays.asList(
            new User(1,"Alkorr","Hanane"),
            new User(2,"Al","Ha"),
            new User(3,"Alk","Han"),
            new User(4,"Alkor","Hanan")
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER')")
//    @Secured("ROLE_USER")
    public List<User> getAllUsers() {
        System.out.println("---- get All Users ---- :");
        USERS.forEach(user -> System.out.println(user));
        return USERS;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public User addNewUser(@RequestBody User user) {
        System.out.println("---- add New User ---- :");
        System.out.println(user);
        return user;
    }

    @DeleteMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('user:write')")
    public Integer deleteUser(@PathVariable("userId") Integer userId) {
        System.out.println("---- delete User : ---- ");
        System.out.println(userId);
        return userId;
    }

    @PutMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('user:write')")
    public User updateUser(@PathVariable("userId") Integer userId, @RequestBody User user) {
        System.out.println("---- update User : ----");
        System.out.println(String.format("%s %s", userId, user));
        return user;
    }
}
