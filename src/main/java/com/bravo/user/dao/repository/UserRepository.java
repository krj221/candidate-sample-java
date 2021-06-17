package com.bravo.user.dao.repository;

import com.bravo.user.dao.model.User;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);
  private static final List<User> MOCK_USERS = new ArrayList<>();

  static {
    MOCK_USERS.add(new User("James"));
    MOCK_USERS.add(new User("John"));
    MOCK_USERS.add(new User("Justin"));
    MOCK_USERS.add(new User("Joe"));
    MOCK_USERS.add(new User("Jill"));
    MOCK_USERS.add(new User("Jane"));
    MOCK_USERS.add(new User("Jack"));
    MOCK_USERS.add(new User("Jet"));
  }

  public List<User> retrieveUsers(){
    LOGGER.info("retrieveUsers");
    return MOCK_USERS;
  }
}
