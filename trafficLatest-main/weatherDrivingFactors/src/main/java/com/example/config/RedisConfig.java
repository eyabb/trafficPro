package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories(basePackages = "com.example.repository")
public class RedisConfig {

  // @Bean
  // public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory
  // connectionFactory) {
  // final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
  // redisTemplate.setConnectionFactory(connectionFactory);
  // redisTemplate.setValueSerializer(new
  // GenericToStringSerializer<>(Object.class));
  // return redisTemplate;
  // }
  // @Bean
  // JedisConnectionFactory jedisConnectionFactory() {
  // return new JedisConnectionFactory();
  // }
  //
  // @Bean
  // public RedisTemplate<String, Object> redisTemplate() {
  // RedisTemplate<String, Object> template = new RedisTemplate<>();
  // template.setConnectionFactory(jedisConnectionFactory());
  // return template;
  // }

  @Bean
  public JedisConnectionFactory connectionFactory() {
    RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
    configuration.setHostName("localhost");
    configuration.setPort(6379);
    return new JedisConnectionFactory(configuration);
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory());
    template.setKeySerializer(new StringRedisSerializer());
    template.setHashKeySerializer(new JdkSerializationRedisSerializer());
    template.setValueSerializer(new JdkSerializationRedisSerializer());
    template.setEnableTransactionSupport(true);
    template.afterPropertiesSet();
    return template;
  }

}
