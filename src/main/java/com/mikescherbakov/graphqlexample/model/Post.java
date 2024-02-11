package com.mikescherbakov.graphqlexample.model;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class Post {

  @NonNull private UUID id;
  @NonNull private String title;
  @NonNull private String text;
  private String category;
  @NonNull private UUID authorId;
  @NonNull private Author author;
}
