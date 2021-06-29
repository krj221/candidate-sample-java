package com.bravo.user.dao.specification;

import com.bravo.user.dao.model.User;
import com.bravo.user.model.filter.UserFilter;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UserSpecification extends AbstractSpecification<User> {

  private final UserFilter filter;

  public UserSpecification(final UserFilter filter){
    this.filter = filter;
  }

  @Override
  public void doFilter(
      Root<User> root,
      CriteriaQuery<?> criteriaQuery,
      CriteriaBuilder criteriaBuilder
  ){
    applyInFilter(root.get("id"), filter.getIds());

    final String nameSubQuery = "name";
    applyStringFilter(root.get("firstName"), filter.getNames(), nameSubQuery);
    applyStringFilter(root.get("middleName"), filter.getNames(), nameSubQuery);
    applyStringFilter(root.get("lastName"), filter.getNames(), nameSubQuery);

    //TODO: filter.getDateFilter();
  }
}
