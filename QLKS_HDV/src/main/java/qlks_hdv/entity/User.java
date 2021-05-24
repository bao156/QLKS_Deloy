package qlks_hdv.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {

  @Id
  @NotBlank(message = "cannot-be-empty")
  @Column(name = "username",unique = true,updatable = false)
  private String username;


  @Column(name = "password")
  private String password;

  @ManyToOne
  @JoinColumn(name = "role_id")
  private Roles role;

  @OneToOne(mappedBy = "user")
  private Staff staff;

  @OneToOne(mappedBy = "user")
  private Customer customer;

}
