package qlks_hdv.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode( exclude="customer")
@Entity
@Table(name = "Users")
public class User {

  @Id
  @Column(name = "username", unique = true, updatable = false)
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "status")
  private Integer status;

  @ManyToOne
  @JoinColumn(name = "role_id")
  private Role roles;

  @OneToOne(mappedBy = "user")
  private Staff staff;

  @OneToOne(mappedBy = "user")
  private Customer customer;

}
