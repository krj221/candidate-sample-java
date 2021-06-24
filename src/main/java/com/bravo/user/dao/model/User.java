package com.bravo.user.dao.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {

  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "middle_name")
  private String middleName;

  @Column(name = "last_name")
  private String lastName;

  public User(){
    super();
    this.id = UUID.randomUUID().toString();
  }
}
