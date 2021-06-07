package qlks_hdv.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import qlks_hdv.entity.compositekey.BookingDetailId;

@Data
@NoArgsConstructor
@Entity(name = "Booking_Detail")
@IdClass(BookingDetailId.class)
public class BookingDetail {

  @Id
  @ManyToOne
  @JoinColumn(name = "bookingcard_id")
  private BookingCard bookingCard;

  @Id
  @ManyToOne
  @JoinColumn(name = "type_id",referencedColumnName = "id")
  private RoomType type;

  @Column(name = "recieve_at")
  @DateTimeFormat(pattern = "yyyy/mm/dd")
  private String recieveAt;

  @Column(name = "back_at")
  @DateTimeFormat(pattern = "yyyy/mm/dd")
  private String backAt;

  @Column(name = "amount")
  private Integer amount;

  @Column(name = "price")
  private Integer price;

}
