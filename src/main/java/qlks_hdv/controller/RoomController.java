package qlks_hdv.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qlks_hdv.request.CreateRoomRequest;
import qlks_hdv.request.UpdateRoomRequest;
import qlks_hdv.response.GetRoomResponse;
import qlks_hdv.service.impl.RoomService;


@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

  private final RoomService roomService;

  @PostMapping
  public ResponseEntity<Void> createRoom(@Valid @RequestBody CreateRoomRequest createRoomRequest) {
    roomService.createaRoom(createRoomRequest);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{roomCode}")
  public ResponseEntity<Void> updateRoom(@PathVariable("roomCode") String roomCode,
      @Valid @RequestBody UpdateRoomRequest updateRoomRequest) {
    roomService.updateRoom(updateRoomRequest, roomCode);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{roomCode}")
  public ResponseEntity<Void> deleteRoom(@PathVariable("roomCode") String roomCode) {
    roomService.deleteRoom(roomCode);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{status}")
  public ResponseEntity<List<GetRoomResponse>> getAllRoomsByStatus(
      @PathVariable("status") String status) {
    return ResponseEntity.ok().body(roomService.getAllRoomsByStatus(status));
  }

  @GetMapping
  public ResponseEntity<List<GetRoomResponse>> getAllRooms() {
    return ResponseEntity.ok().body(roomService.getAllRooms());
  }


}
