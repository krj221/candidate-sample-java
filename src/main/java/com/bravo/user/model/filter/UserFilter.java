package com.bravo.user.model.filter;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.Data;

@Data
public class UserFilter {

  private Set<String> ids;
  private Set<String> names;
  private DateFilter<LocalDateTime> dateFilter;
}
