package com.bravo.user.dao.model.mapper;

import com.bravo.user.App;
import com.bravo.user.dao.model.User;
import com.bravo.user.MapperArgConverter;
import com.bravo.user.model.dto.UserReadDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BindException;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {App.class})
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ResourceMapperTest {

  @Autowired
  private ResourceMapper resourceMapper;

  @ParameterizedTest
  @CsvFileSource(
      resources = ("/ResourceMapperTest/convertUserTest.csv"),
      delimiter = '$',
      lineSeparator = ">"
  )
  public void convertUserTest(
      @ConvertWith(MapperArgConverter.class) User user,
      @ConvertWith(MapperArgConverter.class) UserReadDto userReadDto
  ) {
    Assertions.assertEquals(userReadDto, resourceMapper.convertUser(user));
  }
}