package com.bravo.user.controller;

import com.bravo.user.annotation.SwaggerController;
import com.bravo.user.model.dto.ReflectClassDto;
import com.bravo.user.model.dto.UserDto;
import com.bravo.user.model.filter.UserFilter;
import com.bravo.user.service.UserService;
import com.bravo.user.utility.PageUtil;
import com.bravo.user.utility.ReflectUtil;
import com.bravo.user.validator.UserValidator;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/user")
@SwaggerController
public class UserController {

  private final UserService userService;
  private final UserValidator userValidator;

  public UserController(UserService userService, UserValidator userValidator) {
    this.userService = userService;
    this.userValidator = userValidator;
  }

  @GetMapping(value = "/retrieve/{id}")
  @ResponseBody
  public UserDto retrieve(@PathVariable String id){
    return userService.retrieve(id);
  }

  @PostMapping(value = "/retrieve")
  @ResponseBody
  public List<UserDto> retrieve(
      @RequestBody UserFilter filter,
      @RequestParam(required = false) Integer page,
      @RequestParam(required = false) Integer size,
      BindingResult errors,
      HttpServletResponse httpResponse
  ) throws BindException {

    ValidationUtils.invokeValidator(userValidator, filter, errors);
    if(errors.hasErrors()){
      throw new BindException(errors);
    }
    final PageRequest pageRequest = PageUtil.createPageRequest(page, size);
    return userService.retrieve(filter, pageRequest, httpResponse);
  }
}
