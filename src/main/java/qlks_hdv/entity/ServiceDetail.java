package qlks_hdv.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import qlks_hdv.entity.compositekey.ServiceDetailId;

@Data
@NoArgsConstructor
@Entity(name = "Service_Detail")
@IdClass(ServiceDetailId.class)
public class ServiceDetail {

  @Id
  @ManyToOne
  @JoinColumn(name = "bookingcard_id")
  private BookingCard bookingCard;

  @Id
  @ManyToOne
  @JoinColumn(name = "service_id")
  private Services service;

  @Column(name = "quantity")
  private Integer quantity;

  @Column(name = "price")
  private Integer price;
}
