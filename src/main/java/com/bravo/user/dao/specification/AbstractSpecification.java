package com.bravo.user.dao.specification;

import com.bravo.user.utility.ValidatorUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public abstract class AbstractSpecification<T> implements Specification<T> {

  private CriteriaBuilder criteriaBuilder;
  private Map<String, List<Predicate>> predicates = new HashMap<>();


  abstract void doFilter(
      Root<T> root,
      CriteriaQuery<?> criteriaQuery,
      CriteriaBuilder criteriaBuilder
  );


  @Override
  public Predicate toPredicate(
      Root<T> root,
      CriteriaQuery<?> criteriaQuery,
      CriteriaBuilder criteriaBuilder
  ) {
    this.criteriaBuilder = criteriaBuilder;
    this.predicates = new HashMap<>();

    doFilter(root, criteriaQuery, criteriaBuilder);
    final List<Predicate> rootPredicates = getPredicates();

    // combine sub-queries to the root query as 'or'
    predicates
        .entrySet().stream()
        .filter(entry -> !"root".equals(entry.getKey()))
        .forEach(entry ->
            getPredicates().add(criteriaBuilder.or(entry.getValue().toArray(new Predicate[0]))));

    if(rootPredicates.isEmpty()){
      rootPredicates.add(criteriaBuilder.isNull(root.get("id")));
    }
    return criteriaQuery
        .distinct(true)
        .where(criteriaBuilder.and(rootPredicates.toArray(new Predicate[0])))
        .getRestriction();
  }

  protected <X, Y extends Collection<X>> void applyInFilter(
      final Expression<X> path,
      final Y values
  ){
    applyInFilter(path, values, null);
  }

  protected <X, Y extends Collection<X>> void applyInFilter(
      final Expression<X> path,
      final Y values,
      final String query
  ){
    if(ValidatorUtil.isInvalid(values)){
      return;
    }
    getPredicates(query).add(path.in(values));
  }

  protected <X extends Collection<String>> void applyStringFilter(
      final Expression<String> path,
      final X values
  ){
    applyStringFilter(path, values, null);
  }

  protected  <X extends Collection<String>> void applyStringFilter(
      final Expression<String> path,
      final X values,
      final String query
  ){
    final Expression<String> targetPath = criteriaBuilder.lower(path);
    final List<Predicate> targetPredicates = getPredicates(query);
    final List<String> inClause = new ArrayList<>();

    for(String s : values){
      final String targetValue = s.toLowerCase();
      final boolean isLike = s.contains("%") || s.contains("*");
      final boolean isNot = s.startsWith("!");

      if(!isLike && !isNot){
        inClause.add(targetValue);
        continue;
      }
      Predicate predicate;
      if(isLike){
        predicate = criteriaBuilder.like(targetPath, targetValue);
      } else {
        predicate = criteriaBuilder.equal(targetPath, targetValue);
      }

      if(isNot){
        // exclude "not" from sub-queries
        getPredicates().add(criteriaBuilder.or(path.isNull(), criteriaBuilder.not(predicate)));
      } else {
        targetPredicates.add(predicate);
      }
    }
    if(!inClause.isEmpty()){
      applyInFilter(targetPath, inClause, query);
    }
  }

  private List<Predicate> getPredicates(){
    return getPredicates("root");
  }

  private List<Predicate> getPredicates(String query){
    if(!predicates.containsKey(query)){
      predicates.put(query, new ArrayList<>());
    }
    return predicates.get(query);
  }
}
