package com.bravo.user.model.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class UserReadDto {

  private String id;
  private String name;
  private LocalDateTime updated;
}
