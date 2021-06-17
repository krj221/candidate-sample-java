package com.bravo.user.service;

import com.bravo.user.dao.model.User;
import com.bravo.user.dao.repository.UserRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> retrieveUsers(){
    LOGGER.info("retrieveUsers");
    return userRepository.retrieveUsers();
  }
}
