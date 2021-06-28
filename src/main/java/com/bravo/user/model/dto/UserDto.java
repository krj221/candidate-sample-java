package com.bravo.user.model.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class UserDto {

  private String id;
  private String name;
  private LocalDateTime updated;
}
