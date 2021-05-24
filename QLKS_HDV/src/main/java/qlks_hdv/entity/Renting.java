package qlks_hdv.entity;

import java.io.Serializable;
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
import qlks_hdv.entity.compositekey.RentingCompositeKey;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Renting")
@IdClass(RentingCompositeKey.class)
public class Renting implements Serializable {

  @Id
  @Column(name = "bookingcard_id")
  private Integer bookingcardId;

  @Id
  @Column(name = "room_code")
  private String roomCode;

  @Column(name = "rent_at")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private String rentAt;

  @Column(name = "back_at")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private String backAt;

  @ManyToOne
  @JoinColumn(name = "staff_id")
  private Staff staff;


}
