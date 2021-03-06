package qlks_hdv.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

  @Column(name = "status")
  private String status;

  @Column(name = "description")
  private String description;

  @Column(name = "image")
  private String image;

  @ManyToOne
  @JoinColumn(name = "type_id")
  private RoomType type;

}
