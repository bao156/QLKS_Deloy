package qlks_hdv.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "Hotel")
public class Hotel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "hotel_name")
  private String hotelName;

  @Column(name = "description")
  private String description;

  @Column(name = "logo")
  private String logo;

  @OneToMany(mappedBy = "hotel")
  private List<Role> roles = new ArrayList<>();

}
