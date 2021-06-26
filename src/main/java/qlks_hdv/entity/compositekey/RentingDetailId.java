package qlks_hdv.entity.compositekey;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RentingDetailId implements Serializable {

  private Integer bookingCard;
  private String room;

}
