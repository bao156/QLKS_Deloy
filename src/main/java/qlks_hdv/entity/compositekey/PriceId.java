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
public class PriceId implements Serializable {

  private Integer typeId;

  private Boolean isWeekend;

}
