package com.mikescherbakov.graphqlexample.dao;

import com.mikescherbakov.graphqlexample.model.Author;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AuthorDao {

  private final List<Author> authors;

  public AuthorDao(List<Author> authors) {
    this.authors = authors;
  }

  public Author getAuthorById(UUID id) {
    return authors.stream()
        .filter(author -> id.equals(author.getId()))
        .findFirst()
        .orElseThrow(RuntimeException::new);
  }

  public List<Author> getAuthors(int count, int offset) {
    return authors.stream()
        .skip(offset)
        .limit(count)
        .toList();
  }
}