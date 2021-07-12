package com.bravo.user.validator;

import com.bravo.user.MapperArgConverter;
import com.bravo.user.enumerator.Crud;
import com.bravo.user.model.dto.UserSaveDto;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;

import static org.junit.jupiter.api.Assertions.*;

class UserValidatorTest {


    @ParameterizedTest
    @CsvFileSource(
            resources = ("/validateCreateTests.csv"),
            delimiter = '$',
            lineSeparator = ">"

    )
    void validateCreate(@ConvertWith(MapperArgConverter.class)UserSaveDto userSaveDto, boolean isValid) throws BindException {
         Executable method = ()->new UserValidator().validate(Crud.CREATE,userSaveDto,new BeanPropertyBindingResult(userSaveDto,"userSaveDto"));

         if(isValid){
             assertDoesNotThrow(method);
         }else{
             assertThrows(BindException.class,method);
         }

    }

    @ParameterizedTest
    @CsvFileSource(
            resources = ("/validateUpdateTests.csv"),
            delimiter = '$',
            lineSeparator = ">"

    )
    void validateUpdate(@ConvertWith(MapperArgConverter.class)UserSaveDto userSaveDto,boolean isValid) throws BindException {
        Executable method = ()->new UserValidator().validate(Crud.UPDATE,userSaveDto,new BeanPropertyBindingResult(userSaveDto,"userSaveDto"));

        if(isValid){
            assertDoesNotThrow(method);
        }else{
            assertThrows(BindException.class,method);
        }

    }


}