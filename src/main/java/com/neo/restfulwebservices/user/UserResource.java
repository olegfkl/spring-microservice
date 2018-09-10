package com.neo.restfulwebservices.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController

public class UserResource {

 private static List<User> users = new ArrayList<>();

    @Autowired
    private UserDaoService service;

    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping(path = "/test")
    public List<User> testIt() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
//        List<User> myObjects = mapper.readValue(jsonInput, new TypeReference<List<MyClass>>(){});

        users = mapper.readValue(new File("/Users/oleg/workspace/spring-microservice/src/main/java/com/neo/restfulwebservices/user/user.json"), new TypeReference<List<User>>(){});
        return users;
    }

    @GetMapping(path = "/users/{userId}")
    public User retrieveUser(@PathVariable int userId) {
        User user = service.findOne(userId);
        if(user==null)
            throw new UserNotFoundException("id-" + userId);

        return user;

    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User save = service.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(save.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
