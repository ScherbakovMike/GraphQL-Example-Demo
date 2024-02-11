package com.mikescherbakov.graphqlexample.dao;

import com.mikescherbakov.graphqlexample.model.Post;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class PostDao {

  private final List<Post> posts;

  public PostDao(List<Post> posts) {
    this.posts = posts;
  }

  public List<Post> getRecentPosts(int count, int offset) {
    return posts.stream()
        .skip(offset)
        .limit(count)
        .toList();
  }

  public List<Post> getAuthorPosts(UUID authorId) {
    return posts.stream()
        .filter(post -> authorId.equals(post.getAuthorId()))
        .toList();
  }

  public void savePost(Post post) {
    posts.add(post);
  }
}