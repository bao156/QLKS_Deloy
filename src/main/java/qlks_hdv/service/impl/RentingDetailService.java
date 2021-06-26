package qlks_hdv.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import qlks_hdv.entity.BookingCard;
import qlks_hdv.entity.BookingDetail;
import qlks_hdv.entity.Customer;
import qlks_hdv.entity.RentingDetail;
import qlks_hdv.entity.Room;
import qlks_hdv.entity.RoomStatus;
import qlks_hdv.entity.Staff;
import qlks_hdv.exception.BadRequestException;
import qlks_hdv.exception.NotFoundException;
import qlks_hdv.mapper.CustomerMapper;
import qlks_hdv.mapper.RentingMapper;
import qlks_hdv.repository.BookingCardRepository;
import qlks_hdv.repository.BookingDetailRepository;
import qlks_hdv.repository.CustomerRepository;
import qlks_hdv.repository.RentingDetailRepository;
import qlks_hdv.repository.RoomRepostiory;
import qlks_hdv.repository.StaffRepository;
import qlks_hdv.request.CreateCustomerRentingRequest;
import qlks_hdv.request.CreateRentingDetailRequest;
import qlks_hdv.service.IRentingDetailService;

@Data
@Lazy
@RequiredArgsConstructor
@Service
public class RentingDetailService implements IRentingDetailService {

  private final BookingCardRepository bookingCardRepository;
  private final RoomRepostiory roomRepostiory;
  private final RentingDetailRepository rentingDetailRepository;
  private final StaffRepository staffRepository;
  private final CustomerRepository customerRepository;
  private final RentingMapper rentingMapper;
  private final CustomerMapper customerMapper;
  private final BookingDetailRepository bookingDetailRepository;

  @Override
  @Transactional
  public void createRentingDetail(CreateRentingDetailRequest createRentingDetailRequest) {

    BookingCard bookingCard = bookingCardRepository
        .findById(createRentingDetailRequest.getBookingCardId())
        .orElseThrow(() -> new NotFoundException("booking-card-not-found"));

    Room room = roomRepostiory.findById(createRentingDetailRequest.getRoomCode())
        .orElseThrow(() -> new NotFoundException("room-not-found"));

    if(!room.getStatus().equals(RoomStatus.Empty))
    {
      throw new BadRequestException("status-is-not-valid");
    }

    List<RentingDetail> rentingDetails = rentingDetailRepository
        .findAllByBookingCardBookingIdAndRoomTypeId(createRentingDetailRequest.getBookingCardId(),
            room.getType().getId());

    BookingDetail bookingDetail = bookingDetailRepository
        .findByBookingCardBookingIdAndTypeId(bookingCard.getBookingId(), room.getType().getId())
        .orElseThrow(() -> new NotFoundException("booking-this-type-not-exits"));

    if (rentingDetails.size() + 1 > bookingDetail.getAmount()) {
      throw new BadRequestException("booking-card-full-for-this-type");
    }
    Staff staff = staffRepository.findByUserUsername(createRentingDetailRequest.getUsername())
        .orElseThrow(() -> new NotFoundException("staff-not-found"));

    if(bookingCard.getStatus().equals("Reservated"))
    {
      bookingCard.setStatus("Renting");
    }

    room.setStatus(RoomStatus.Full);
    RentingDetail rentingDetail = rentingMapper
        .mapToRenting(createRentingDetailRequest, bookingCard, room, staff);

    for (CreateCustomerRentingRequest createCustomerRentingRequest : createRentingDetailRequest
        .getCustomers()) {
      Customer customer = new Customer();
      if (customerRepository.existsByCMND(createCustomerRentingRequest.getCMND())) {
        customer = customerRepository.findByCMND(createCustomerRentingRequest.getCMND())
            .orElseThrow(() -> new NotFoundException("user-not-found"));
      } else {
        customer = customerMapper.mapToCustomer(createCustomerRentingRequest);
        customerRepository.save(customer);
      }
      rentingDetail.getCustomers().add(customer);
    }

    rentingDetailRepository.save(rentingDetail);


  }


}
