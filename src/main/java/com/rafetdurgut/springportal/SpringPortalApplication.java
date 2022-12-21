package com.rafetdurgut.springportal;

import com.rafetdurgut.springportal.models.Post;
import com.rafetdurgut.springportal.models.User;
import com.rafetdurgut.springportal.models.UserRole;
import com.rafetdurgut.springportal.services.PostService;
import com.rafetdurgut.springportal.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SpringPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPortalApplication.class, args);
    }
    @Bean
    BCryptPasswordEncoder passwordEncoder()
    {
        return  new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService, PostService postService) {
        return args ->
        {
            userService.saveRole(new UserRole(null, "ROLE_USER"));
            userService.saveRole(new UserRole(null, "ROLE_MANAGER"));
            userService.saveRole(new UserRole(null, "ROLE_ADMIN"));

            userService.saveUser(new User(null, "rafet", "rdurgut", "12345", new ArrayList<>()));

            userService.addRoleToUser("rdurgut", "ROLE_USER");
            userService.addRoleToUser("rdurgut", "ROLE_ADMIN");


            Post post = new Post(null, "Test post 1", " Test description 1", System.currentTimeMillis(), System.currentTimeMillis(), userService.getUserById(1L), new ArrayList<>());
            postService.addPost(post);


        };
    }
}
