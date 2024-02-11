package com.mikescherbakov.graphqlexample.configuration;

import com.mikescherbakov.graphqlexample.dao.AuthorDao;
import com.mikescherbakov.graphqlexample.dao.PostDao;
import com.mikescherbakov.graphqlexample.model.Author;
import com.mikescherbakov.graphqlexample.model.Post;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
@RequiredArgsConstructor
public class GraphqlConfiguration {

  private final ApplicationContext applicationContext;

  @Bean
  public AuthorDao authorDao() {
    List<Author> authors = new ArrayList<>();
    for (var authorId = 0; authorId < 10; ++authorId) {
      var author = Author.builder()
          .id(UUID.randomUUID())
          .name("Author " + authorId)
          .thumbnail("http://example.com/authors/" + authorId)
          .posts(new ArrayList<>())
          .build();
      authors.add(author);
    }
    return new AuthorDao(authors);
  }

  @Bean
  @DependsOn({"authorDao"})
  public PostDao postDao() {
    var posts = new ArrayList<Post>();
    var authorDao = applicationContext.getBean(AuthorDao.class);
    for (var postId = 0; postId < 10; ++postId) {
      for (var author : authorDao.getAuthors()) {
        var post = Post.builder()
            .id(UUID.randomUUID())
            .title("Post " + author.getId() + ":" + postId)
            .category("Post category")
            .text("Post " + postId + " + by author " + author.getId())
            .authorId(author.getId())
            .author(author)
            .build();
        posts.add(post);
        author.getPosts().add(post);
      }
    }
    return new PostDao(posts);
  }
}
