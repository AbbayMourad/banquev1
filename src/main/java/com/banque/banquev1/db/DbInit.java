package com.banque.banquev1.db;

import com.banque.banquev1.entity.User;
import com.banque.banquev1.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        // Delete all
        this.userRepository.deleteAll();


        User admin = new User("mourad",passwordEncoder.encode("admin"),true,"ROLE_ADMIN");
        User user = new User("yassir", passwordEncoder.encode("adham"),true,"ROLE_USER");

        List<User> users = Arrays.asList(admin, user);

        // Save Users to data base
        this.userRepository.saveAll(users);

    }
}
