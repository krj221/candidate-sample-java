package com.bravo.user.controller;

import com.bravo.user.dao.model.User;
import com.bravo.user.service.UserService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class UserController {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(value = "/retrieve")
  @ResponseBody
  public List<User> retrieveUsers(){
    LOGGER.info("retrieveUsers");
    return userService.retrieveUsers();
  }
}
