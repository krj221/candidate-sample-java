package com.bravo.user.dao.specification;

import com.bravo.user.dao.model.User;
import com.bravo.user.model.filter.UserFilter;
import com.bravo.user.utility.ValidatorUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification implements Specification<User> {

  private final UserFilter filter;

  public UserSpecification(final UserFilter filter){
    this.filter = filter;
  }

  @Override
  public Predicate toPredicate(
      Root<User> root,
      CriteriaQuery<?> criteriaQuery,
      CriteriaBuilder criteriaBuilder) {

    List<Predicate> predicates = new ArrayList<>();
    if(ValidatorUtil.isValid(filter.getIds())){
      predicates.add(root.get("id").in(filter.getIds()));
    }
    if(ValidatorUtil.isValid(filter.getNames())){
      List<Predicate> namePredicates = new ArrayList<>();
      namePredicates.add(root.get("firstName").in(filter.getNames()));
      namePredicates.add(root.get("middleName").in(filter.getNames()));
      namePredicates.add(root.get("lastName").in(filter.getNames()));

      predicates.add(criteriaBuilder.or(namePredicates.toArray(new Predicate[0])));
    }
    //TODO: DateFilter

    if(predicates.isEmpty()){
      predicates.add(criteriaBuilder.isNull(root.get("id")));
    }
    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
  }
}
