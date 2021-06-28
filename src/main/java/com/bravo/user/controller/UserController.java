package com.bravo.user.controller;

import com.bravo.user.model.UserDto;
import com.bravo.user.service.UserService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(value = "/retrieve/{id}")
  @ResponseBody
  public UserDto retrieve(@PathVariable String id){
    return userService.retrieve(id);
  }

  @GetMapping(value = "/retrieve")
  @ResponseBody
  public List<UserDto> retrieve(){
    return userService.retrieve();
  }
}
