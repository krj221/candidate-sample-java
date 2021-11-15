package com.bravo.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bravo.user.App;
import com.bravo.user.dao.model.User;
import com.bravo.user.dao.model.mapper.ResourceMapper;
import com.bravo.user.dao.repository.UserRepository;
import com.bravo.user.dao.specification.UserNameFuzzySpecification;
import com.bravo.user.dao.specification.UserSpecification;
import com.bravo.user.model.dto.UserReadDto;
import com.bravo.user.model.filter.UserNameFuzzyFilter;
import com.bravo.user.utility.PageUtil;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {App.class})
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {

  @Autowired
  private HttpServletResponse httpResponse;

  @Autowired
  private UserService userService;

  @MockBean
  private ResourceMapper resourceMapper;

  @MockBean
  private UserRepository userRepository;

  private List<UserReadDto> dtoUsers;

  @BeforeEach
  public void beforeEach(){
    final List<Integer> ids = IntStream
        .range(1, 10)
        .boxed()
        .collect(Collectors.toList());

    final Page<User> mockPage = mock(Page.class);
    when(userRepository.findAll(any(UserNameFuzzySpecification.class), any(PageRequest.class)))
        .thenReturn(mockPage);

    final List<User> daoUsers = ids.stream()
        .map(id -> createUser(Integer.toString(id)))
        .collect(Collectors.toList());
    when(mockPage.getContent()).thenReturn(daoUsers);
    when(mockPage.getTotalPages()).thenReturn(9);

    this.dtoUsers = ids.stream()
        .map(id -> createUserReadDto(Integer.toString(id)))
        .collect(Collectors.toList());
    when(resourceMapper.convertUsers(daoUsers)).thenReturn(dtoUsers);
  }

  @Test
  public void retrieveByName() {
    final String input = "input";
    final PageRequest pageRequest = PageUtil.createPageRequest(null, null);
    final List<UserReadDto> results = userService.retrieveByName(input, pageRequest, httpResponse);
    assertEquals(dtoUsers, results);
    assertEquals(httpResponse.getHeader("page-count"), "9");
    assertEquals(httpResponse.getHeader("page-number"), "1");
    assertEquals(httpResponse.getHeader("page-size"), "20");

    final UserNameFuzzyFilter filter = new UserNameFuzzyFilter(input);
    final UserNameFuzzySpecification specification = new UserNameFuzzySpecification(filter);
    verify(userRepository).findAll(specification, pageRequest);
  }

  @Test
  public void retrieveByNamePaged() {
    final String input = "input2";
    final PageRequest pageRequest = PageUtil.createPageRequest(2, 5);
    final List<UserReadDto> results = userService.retrieveByName(input, pageRequest, httpResponse);
    assertEquals(dtoUsers, results);
    assertEquals(httpResponse.getHeader("page-count"), "9");
    assertEquals(httpResponse.getHeader("page-number"), "2");
    assertEquals(httpResponse.getHeader("page-size"), "5");

    final UserNameFuzzyFilter filter = new UserNameFuzzyFilter(input);
    final UserNameFuzzySpecification specification = new UserNameFuzzySpecification(filter);
    verify(userRepository).findAll(specification, pageRequest);
  }

  private User createUser(final String id){
    final User user = new User();
    user.setId(id);
    return user;
  }

  private UserReadDto createUserReadDto(final String id){
    final UserReadDto user = new UserReadDto();
    user.setId(id);
    return user;
  }
}