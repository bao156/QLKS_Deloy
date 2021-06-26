package qlks_hdv.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import qlks_hdv.entity.compositekey.RentingDetailId;

@Data
@NoArgsConstructor
@EqualsAndHashCode( exclude="customers")
@Entity
@Table(name = "Renting_Detail")
@IdClass(RentingDetailId.class)
public class RentingDetail implements Serializable {

  @Id
  @ManyToOne
  @JoinColumn(name = "bookingcard_id", referencedColumnName = "id")
  private BookingCard bookingCard;

  @Id
  @ManyToOne
  @JoinColumn(name = "room_code", referencedColumnName = "code")
  private Room room;


  @Column(name = "rent_at")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private String rentAt;

  @Column(name = "back_at")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private String backAt;

  @ManyToOne
  @JoinColumn(name = "staff_id")
  private Staff staff;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinTable(name = "Customer_Renting",
      joinColumns = {@JoinColumn(name = "bookingcard_id"), @JoinColumn(name = "room_code")},
      inverseJoinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "id")})
  private Set<Customer> customers = new HashSet<>();
}
