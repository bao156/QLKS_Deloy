package qlks_hdv.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import qlks_hdv.entity.compositekey.PriceId;

@Data
@NoArgsConstructor
@Entity
@IdClass(PriceId.class)
@Table(name = "Price")
public class Price {

  @Id
  @Column(name = "type_id")
  private Integer typeId;

  @Id
  @Column(name = "weekend")
  private Boolean isWeekend;

  @Column(name = "price")
  private Integer price;


}
