package com.bravo.user.config;

import com.bravo.user.App;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {App.class})
@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = {
    "server.port=9090",
})
public class AppConfigTest {

  @Autowired
  private AppConfig appConfig;

  @Test
  public void serverPort(){
    final int expect = 9090;
    final int actual = appConfig.serverPort();
    Assertions.assertEquals(expect, actual);
  }
}
