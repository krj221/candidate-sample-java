package com.bravo.user.validator;

import com.bravo.user.exception.BadRequestException;
import com.bravo.user.model.dto.UserSaveDto;
import com.bravo.user.utility.ValidatorUtil;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class UserValidator extends CrudValidator {

  public void validateId(String id){
    if(ValidatorUtil.isInvalid(id)){
      throw new BadRequestException("'id' is required");
    }
  }

  @Override
  protected void validateCreate(Object o, Errors errors) {

    UserSaveDto instance = (UserSaveDto) o;

    // required fields
    if(ValidatorUtil.isInvalid(instance.getFirstName())){
      errors.reject("'firstName' is required");
    }
    if(ValidatorUtil.isInvalid(instance.getLastName())){
      errors.reject("'lastName' is required");
    }
  }

  @Override
  protected void validateUpdate(Object o, Errors errors) {

    UserSaveDto instance = (UserSaveDto) o;

    if(ValidatorUtil.isEmpty(instance, "id", "updated")){
      errors.reject("'request' modifiable field(s) are required");
    }
  }
}
