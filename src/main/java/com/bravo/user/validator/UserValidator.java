package com.bravo.user.validator;

import com.bravo.user.model.filter.UserFilter;
import com.bravo.user.utility.ValidatorUtil;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return UserFilter.class.equals(clazz);
  }

  @Override
  public void validate(Object o, Errors errors) {

    UserFilter filter = (UserFilter) o;

    if(ValidatorUtil.isInvalid(filter.getIds())){
      errors.reject("'ids' are invalid");
    }
    if(ValidatorUtil.isInvalid(filter.getNames())){
      errors.reject("'names' are invalid");
    }
    if(ValidatorUtil.isInvalid(filter.getDateFilter())){
      errors.reject("'dateFilter' is invalid");
    }
  }
}
