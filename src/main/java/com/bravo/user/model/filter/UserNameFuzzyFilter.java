package com.bravo.user.model.filter;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class UserNameFuzzyFilter {

  private String name;
}
