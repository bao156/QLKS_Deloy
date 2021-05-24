package qlks_hdv.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import qlks_hdv.entity.compositekey.BookingCompositeKey;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Booking")
@IdClass(BookingCompositeKey.class)
public class BookingDetail implements Serializable {

  @Id
  @Column(name = "bookingcard_id")
  private Integer bookingcardId;

  @Id
  @Column(name = "type_id")
  private Integer typeId;

  @Column(name = "recieveAt")
  @DateTimeFormat(pattern = "yyyy-mm-dd")
  private String recieveAt;

  @Column(name = "backAt")
  @DateTimeFormat(pattern = "yyyy-mm-dd")
  private String backAt;

  @Column(name = "amount")
  private Integer amount;


}
