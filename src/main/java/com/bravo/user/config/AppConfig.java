package com.bravo.user.config;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Value("${server.port:8080}")
  private int serverPort;

  @Bean
  public int serverPort(){
    return serverPort;
  }

  @Bean
  public MapperFacade mapperFacade(){
    return new DefaultMapperFactory.Builder().build().getMapperFacade();
  }
}
