package com.bravo.user.model.filter;

import java.time.temporal.Temporal;
import lombok.Data;

@Data
public class DateFilter<T extends Temporal> {

  private T start;
  private T until;
}
