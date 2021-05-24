package qlks_hdv.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Discount")
public class Discount {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Integer discountId;

  @Column(name = "discount_name")
  private String discountName;

  @Column(name = "discount_value")
  private Float discountValue;

  @Column(name = "use_at")
  @DateTimeFormat(pattern = "yyyy/mm/dd")
  private String useAt;

  @Column(name = "end_at")
  @DateTimeFormat(pattern = "yyyy/mm/dd")
  private String endAt;

  @OneToMany(mappedBy = "discount")
  private List<BookingCard> bookingCard;


}
