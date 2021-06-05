package qlks_hdv.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qlks_hdv.response.GetRoomTypeWithPriceResponse;
import qlks_hdv.service.impl.RoomTypeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/types")
@RequiredArgsConstructor
public class RoomTypeController {

  private final RoomTypeService roomTypeService;

  @GetMapping("/{dateOfWeek}")
  public ResponseEntity<List<GetRoomTypeWithPriceResponse>> getAllRoomTypesByDate(
      @PathVariable("dateOfWeek") Integer date) {
    return ResponseEntity.ok().body(roomTypeService.getRoomTypesByDate(date));
  }

}
