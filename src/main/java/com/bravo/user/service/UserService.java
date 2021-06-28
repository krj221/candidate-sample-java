package com.bravo.user.service;

import com.bravo.user.dao.model.User;
import com.bravo.user.dao.model.mapper.ResourceMapper;
import com.bravo.user.dao.repository.UserRepository;
import com.bravo.user.exception.DataNotFoundException;
import com.bravo.user.model.dto.UserDto;
import com.bravo.user.model.filter.UserFilter;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

  private final UserRepository userRepository;
  private final ResourceMapper resourceMapper;

  public UserService(UserRepository userRepository, ResourceMapper resourceMapper) {
    this.userRepository = userRepository;
    this.resourceMapper = resourceMapper;
  }

  public UserDto retrieve(final String id){
    Optional<User> user = userRepository.findById(id);
    if(user.isPresent()){
      LOGGER.info("found user {}", id);
      return resourceMapper.convertUser(user.get());
    }
    final String message = String.format("user '%s' doesn't exist", id);
    LOGGER.warn(message);
    throw new DataNotFoundException(message);
  }

  public List<UserDto> retrieve(final UserFilter filter, final PageRequest pageRequest){
    List<UserDto> users = resourceMapper.convertUsers(userRepository.findAll());
    LOGGER.info("found {} user(s)", users.size());
    return users;
  }
}
