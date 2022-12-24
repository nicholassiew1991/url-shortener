package com.github.nicholas1991.urlshortener.webapi.dataaccess.entities;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Link {

  @Id
  private String id;

  @NonNull
  @Indexed(unique = true)
  private String code;

  @NonNull
  private String originalUrl;

  @NonNull
  private LocalDateTime createdDateTime;
}
