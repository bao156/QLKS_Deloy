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
public class ServiceDetailId implements Serializable {

  private Integer bookingCard;
  private Integer service;
}
