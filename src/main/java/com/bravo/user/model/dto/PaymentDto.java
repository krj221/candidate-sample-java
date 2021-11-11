package com.bravo.user.model.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class PaymentDto {

  private String id;
  private String cardNumberLast4;
  private Integer expiryMonth;
  private Integer expiryYear;
  private LocalDateTime updated;
}
