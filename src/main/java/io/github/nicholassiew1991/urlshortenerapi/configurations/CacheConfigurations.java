package io.github.nicholassiew1991.urlshortenerapi.configurations;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching
public class CacheConfigurations {

  @Bean("cacheManager")
  public CacheManager redisCacheManager(LettuceConnectionFactory lettuceConnectionFactory) {

    RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
      .disableCachingNullValues()
      .entryTtl(Duration.ofMinutes(10))
      .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()));

    return RedisCacheManager.RedisCacheManagerBuilder
      .fromConnectionFactory(lettuceConnectionFactory)
      .cacheDefaults(redisCacheConfiguration)
      .build();
  }
}
