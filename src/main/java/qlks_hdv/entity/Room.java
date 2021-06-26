package qlks_hdv.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Room")
public class Room {

  @Id
  @Column(name = "code")
  private String roomCode;

  @Column(name = "description")
  private String description;

  @Column(name = "image")
  private String image;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private RoomStatus status;

  @ManyToOne
  @JoinColumn(name = "type_id")
  private RoomType type;

  @OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private List<RentingDetail> rentings = new ArrayList<>();

}
