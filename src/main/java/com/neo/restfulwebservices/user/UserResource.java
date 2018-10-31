package com.neo.restfulwebservices.user;

import com.neo.restfulwebservices.exception.UserNotFoundException;
import com.neo.restfulwebservices.services.PostDaoService;
import com.neo.restfulwebservices.services.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController

public class UserResource {

    @Autowired
    private UserDaoService userService;

    @Autowired
    private PostDaoService postService;

    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers() {
        return userService.findAll();
    }

    @GetMapping(path = "/users/{userId}")
    public User retrieveUser(@PathVariable int userId) {
        User user = userService.findOne(userId);
        if (user == null)
            throw new UserNotFoundException("id-" + userId);
        return user;

    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User save = userService.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(save.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users/{userId}/posts")
    public List<Post> retrieveAllPosts(@PathVariable int userId) {
        return postService.findAllPosts(userId);
        //add logs
    }

    @PostMapping("/users/{userId}/posts")
    public ResponseEntity<Post> createPost(@PathVariable int userId, @RequestBody Post post){
        Post response = postService.save(post, userId);
        return new ResponseEntity<Post>(response, HttpStatus.CREATED);
        //add location
    }
}
