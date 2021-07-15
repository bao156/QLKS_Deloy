package qlks_hdv.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qlks_hdv.request.CreateRoomRequest;
import qlks_hdv.request.UpdateRoomRequest;
import qlks_hdv.response.GetRoomResponseWithPrice;
import qlks_hdv.service.impl.RoomService;

@CrossOrigin(origins = "*", maxAge = 3600)
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

  @GetMapping
  public ResponseEntity<List<GetRoomResponseWithPrice>> getAllRooms() {
    return ResponseEntity.ok().body(roomService.getAllRooms());
  }

  @GetMapping("/room-type/{roomTypeId}")
  public ResponseEntity<List<GetRoomResponseWithPrice>> getAllRoomsByRoomTypeId(
      @PathVariable("roomTypeId") Integer roomTypeId) {
    return ResponseEntity.ok().body(roomService.getAllRoomsByRoomTypeId(roomTypeId));
  }

  @GetMapping("/detail")
  public ResponseEntity<GetRoomResponseWithPrice> getRoomDetail(@RequestParam String roomCode) {
    return ResponseEntity.ok().body(roomService.getRoomDetail(roomCode));
  }

}
