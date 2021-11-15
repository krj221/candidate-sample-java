package com.bravo.user.model.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AddressDto {

  private String id;
  private String address;
  private String line1;
  private String line2;
  private String city;
  private String state;
  private String zip;
  private LocalDateTime updated;
}
