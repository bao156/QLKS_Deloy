package qlks_hdv.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Booking_card")
public class BookingCard {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer bookingId;

  @Column(name = "price")
  private Integer price;

  @Column(name = "status")
  private String status;

  @Column(name = "complete_at")
  private String completeAt;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @ManyToOne
  @JoinColumn(name = "discount_id")
  private Discount discount;

  @OneToMany(mappedBy = "bookingCard", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private List<BookingDetail> bookingDetailList;


}
