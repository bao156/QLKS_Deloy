package qlks_hdv.service.impl;

import javax.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import qlks_hdv.entity.BookingCard;
import qlks_hdv.entity.ServiceDetail;
import qlks_hdv.entity.Services;
import qlks_hdv.exception.NotFoundException;
import qlks_hdv.mapper.ServiceDetailMapper;
import qlks_hdv.repository.BookingCardRepository;
import qlks_hdv.repository.ServiceDetailRepository;
import qlks_hdv.repository.ServiceRepository;
import qlks_hdv.request.CreateBookingCardRequest;
import qlks_hdv.request.CreateServiceDetailRequest;
import qlks_hdv.service.IServiceDetail;

@Data
@RequiredArgsConstructor
@Service
public class ServiceDetailService implements IServiceDetail {

  private final ServiceDetailRepository serviceDetailRepository;
  private final ServiceRepository serviceRepository;
  private final BookingCardRepository bookingCardRepository;
  private final ServiceDetailMapper serviceDetailMapper;
  private final BookingCardService bookingCardService;

  @Override
  @Transactional
  public void addServiceDetail(CreateServiceDetailRequest createServiceDetailRequest) {

    if (!bookingCardRepository.existsBookingCardByStatusAndCustomerUserUsername("Reservated",
        createServiceDetailRequest.getUsername())) {
      bookingCardService.createBookingCard(
          new CreateBookingCardRequest(createServiceDetailRequest.getUsername(), ""));
    }
    BookingCard bookingCard = bookingCardRepository
        .findBookingCardByStatusAndCustomerUserUsername("Reservated",
            createServiceDetailRequest.getUsername())
        .orElseThrow(() -> new NotFoundException("booking-card-not-found"));

    Services service = serviceRepository
        .findByServiceName(createServiceDetailRequest.getNameService())
        .orElseThrow(() -> new NotFoundException("roomtype-not-found"));

    ServiceDetail serviceDetail = serviceDetailMapper
        .mapToServiceDetail(createServiceDetailRequest, bookingCard, service);
    serviceDetailRepository.save(serviceDetail);
  }
}
