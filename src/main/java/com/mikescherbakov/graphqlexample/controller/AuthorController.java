package com.mikescherbakov.graphqlexample.controller;

import com.mikescherbakov.graphqlexample.dao.AuthorDao;
import com.mikescherbakov.graphqlexample.model.Author;
import com.mikescherbakov.graphqlexample.model.Post;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AuthorController {

  private final AuthorDao authorDao;

  @SchemaMapping
  public Author author(Post post) {
    return authorDao.getAuthorById(post.getAuthorId());
  }

  @QueryMapping
  public List<Author> getAuthors(@Argument int count, @Argument int offset) {
    return authorDao.getAuthors(count, offset);
  }
}
