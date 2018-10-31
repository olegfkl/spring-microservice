package com.neo.restfulwebservices.services;

import com.neo.restfulwebservices.user.Post;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostDaoService {

    private static List<Post> posts = new ArrayList<>();

    private Integer postCount = 5;

    static {
        posts.add(new Post(1, 1, "Hello beautiful world"));
        posts.add(new Post(2, 2, "Such a bad day"));
        posts.add(new Post(3, 2, "Shall we play tennis today!?"));
        posts.add(new Post(4, 1, "What movie would you recommend to watch?"));
    }

    public List<Post> findAllPosts(int id) {
        List<Post> list = new ArrayList<>();
        for (Post post : posts) {
            if (post.getUser() == id) {
                list.add(post);
            }
        }
        return list;
    }

    public Post save(Post post, int userId) {
        if (post.getId() == null) {
            post.setId(postCount++);
        }
        post.setUser(userId);
        posts.add(post);
        return post;
    }
}
