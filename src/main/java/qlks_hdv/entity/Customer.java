package qlks_hdv.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "rentings")
@Entity
@Table(name = "Customer")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "firstname")
  private String firstName;

  @Column(name = "lastname")
  private String lastName;

  @Column(name = "phone")
  private String phone;

  @Column(name = "email")
  private String email;

  @Column(name = "CMND", unique = true)
  private String CMND;

  //XÓA CUSTOMER LÀ CHA XÓA LUÔN USER
  @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
  @JoinColumn(name = "username")
  private User user;

  @OneToMany(mappedBy = "customer")
  private List<BookingCard> bookingCards = new ArrayList<>();

  @ManyToMany(mappedBy = "customers", fetch = FetchType.LAZY)
  private Set<RentingDetail> rentings = new HashSet<>();

}

