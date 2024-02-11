package com.mikescherbakov.graphqlexample.controller;

import com.mikescherbakov.graphqlexample.dao.PostDao;
import com.mikescherbakov.graphqlexample.model.Post;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class PostController {

  private final PostDao postDao;

  @QueryMapping
  public List<Post> recentPosts(@Argument int count, @Argument int offset) {
    return postDao.getRecentPosts(count, offset);
  }

  @MutationMapping
  public Post createPost(@Argument String title, @Argument String text,
      @Argument String category, @Argument UUID authorId) {
    var post = Post.builder()
        .id(UUID.randomUUID())
        .title(title)
        .text(text)
        .category(category)
        .authorId(authorId)
        .build();
    postDao.savePost(post);

    return post;
  }
}
