package com.bravo.user.controller;

import com.bravo.user.annotation.SwaggerController;
import com.bravo.user.model.dto.LoginDto;
import com.bravo.user.service.LoginService;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/login")
@SwaggerController
public class LoginController {

  private final LoginService loginService;

  public LoginController(LoginService loginService) {
    this.loginService = loginService;
  }

  @PostMapping
  public void login(final @RequestBody LoginDto request, HttpServletResponse httpResponse){
    if(request.getUsername() == null){
      throw new IllegalArgumentException("'username' is required");
    }
    if(request.getPassword() == null){
      throw new IllegalArgumentException("'password' is required");
    }
    loginService.login(request);
    httpResponse.setStatus(204);
  }
}
