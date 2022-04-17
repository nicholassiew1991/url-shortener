package io.github.nicholassiew1991.urlshortenerapi.repositories.entities;

import com.mongodb.lang.NonNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("links")
public class LinkEntity {

  @Id
  private String id;

  @NonNull
  @Indexed(unique = true)
  private String code;

  @NonNull
  private String originalUrl;
}
