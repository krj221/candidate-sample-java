package com.bravo.user.utility;

import java.util.Collection;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidatorUtil {

  public static <T> boolean isInvalid(T value){
    return !isValid(value);
  }

  public static <T> boolean isValid(T value){

    boolean isValid;
    if(value instanceof Collection<?>){
      isValid = isCollectionValid((Collection<?>)value);
    }
    else if(value instanceof String){
      isValid = isStringValid((String)value);
    }
    else {
      isValid = value != null;
    }
    return isValid;
  }


  private static boolean isCollectionValid(Collection<?> collection){
    return !collection.stream()
        .map(ValidatorUtil::isValid)
        .collect(Collectors.toSet())
        .contains(false);
  }

  private static boolean isStringValid(String string){
    return string != null && !string.trim().isEmpty();
  }
}
