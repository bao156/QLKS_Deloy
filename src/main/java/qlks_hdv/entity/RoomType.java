package qlks_hdv.entity;

import java.util.List;
import javax.persistence.CascadeType;
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
@Table(name = "Room_type")
public class RoomType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "number_of_bed")
  private Integer numberOfBed;

  @Column(name = "name")
  private String name;

  @Column(name = "desciption")
  private String desciption;

  @OneToMany(mappedBy = "type")
  private List<Room> room;

  @OneToMany(mappedBy = "type", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private List<BookingDetail> roomTypeList;

}
