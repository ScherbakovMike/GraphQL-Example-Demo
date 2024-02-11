package com.mikescherbakov.graphqlexample.model;

import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class Author {

  @NonNull private UUID id;
  @NonNull private String name;
  private String thumbnail;
  private List<Post> posts;
}
