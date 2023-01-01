package com.github.nicholas1991.urlshortener.webapi.dataaccess.entities;

import com.mongodb.lang.NonNull;
import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class RedirectRecord {

  @Id
  private ObjectId id;

  @NonNull
  private String code;

  @NonNull
  private boolean isDirectLink;

  @Nullable
  private Map<String, String> queryString;

  @Nullable
  private String referenceFrom;

  @Nullable
  private String userAgent;

  @NonNull
  private LocalDateTime redirectDateTime;
}
