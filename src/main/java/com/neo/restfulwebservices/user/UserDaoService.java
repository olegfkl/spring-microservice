package com.neo.restfulwebservices.user;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.simple.JSONObject;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.IOException;

@Component
public class UserDaoService {

    private static final String filePath = "./user.json";

    private static Integer userCount = 3;

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "Adam", new Date()));
        users.add(new User(2, "Eve", new Date()));
        users.add(new User(3, "Jack", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    ;

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }

    ;

    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }


    public void test() throws IOException {

//        ObjectMapper mapper = new ObjectMapper();
////        FileReader reader = new FileReader(filePath);
//
//      anything = mapper.readValue(new File("/Users/oleg/workspace/spring-microservice/src/main/java/com/neo/restfulwebservices/user/user.json"));
//            return anything;
//        ObjectMapper objectMapper = new ObjectMapper();
//        JSONParser parser = new JSONParser();
//        // read the json file
//
//        FileReader reader = new FileReader(filePath);
//
//
////
//        JSONObject jsonObject =  parser.parse(reader);
//
//        User user = objectMapper.readValue(jsonObject, User.class);
    }
}
