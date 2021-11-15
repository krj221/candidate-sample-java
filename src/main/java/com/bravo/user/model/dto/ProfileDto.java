package com.bravo.user.model.dto;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Id;
import lombok.Data;

@Data
public class ProfileDto {

  private String id;
  private String username;
  private String pictureUrl;
  private LocalDateTime updated;
}
