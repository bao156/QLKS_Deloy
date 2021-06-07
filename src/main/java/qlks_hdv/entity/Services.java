package qlks_hdv.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Service")
public class Services {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "service_name")
  private String serviceName;

  @Column(name = "price")
  private Integer price;

  @Column(name = "image")
  private String image;

  @OneToMany(mappedBy = "service", fetch = FetchType.LAZY)
  private List<ServiceDetail> serviceDetailList;

}
