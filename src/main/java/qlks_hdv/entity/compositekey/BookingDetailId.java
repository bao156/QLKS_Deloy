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
public class BookingDetailId implements Serializable {

  private Integer bookingCardId;
  private Integer typeId;

}
