package com.neo.restfulwebservices.user;

public class Post {

    private Integer id;
    private Integer user;
    private String message;

    protected Post(){}

    public Post(Integer id, Integer user, String message) {
        this.id = id;
        this.user = user;
        this.message = message;
    }
    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Integer getUser() { return user; }

    public void setUser(Integer user) {this.user = user; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }


}
