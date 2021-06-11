package qlks_hdv.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import qlks_hdv.entity.BookingCard;
import qlks_hdv.entity.ServiceDetail;
import qlks_hdv.entity.Services;
import qlks_hdv.entity.compositekey.ServiceDetailId;
import qlks_hdv.exception.NotFoundException;
import qlks_hdv.mapper.ServiceDetailMapper;
import qlks_hdv.mapper.ServiceMapper;
import qlks_hdv.repository.BookingCardRepository;
import qlks_hdv.repository.ServiceDetailRepository;
import qlks_hdv.repository.ServiceRepository;
import qlks_hdv.request.CreateBookingCardRequest;
import qlks_hdv.request.CreateServiceDetailRequest;
import qlks_hdv.response.GetServiceDetailResponse;
import qlks_hdv.response.GetServiceResponse;
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
  private final ServiceMapper serviceMapper;

  @Override
  @Transactional
  public void addServiceDetail(CreateServiceDetailRequest createServiceDetailRequest) {

    if (!bookingCardRepository.existsBookingCardByStatusAndCustomerUserUsername("Processing",
        createServiceDetailRequest.getUsername())) {
      bookingCardService.createBookingCard(
          new CreateBookingCardRequest(createServiceDetailRequest.getUsername(), ""));
    }
    BookingCard bookingCard = bookingCardRepository
        .findBookingCardByStatusAndCustomerUserUsername("Processing",
            createServiceDetailRequest.getUsername())
        .orElseThrow(() -> new NotFoundException("booking-card-not-found"));

    Services service = serviceRepository
        .findByServiceName(createServiceDetailRequest.getNameService())
        .orElseThrow(() -> new NotFoundException("roomtype-not-found"));

    ServiceDetail serviceDetail = serviceDetailMapper
        .mapToServiceDetail(createServiceDetailRequest, bookingCard, service);
    serviceDetailRepository.save(serviceDetail);
    bookingCardService.updatePriceOfBookingCard(bookingCard.getBookingId());
  }

  @Override
  public List<GetServiceDetailResponse> getServiceDetailResponseList(Integer bookingId,
      String username) {

    if (bookingId == 0) {
      BookingCard bookingCard = bookingCardRepository
          .findBookingCardByStatusAndCustomerUserUsername("Processing", username)
          .orElseThrow(() -> new NotFoundException("booking-not-exist"));
      bookingId = bookingCard.getBookingId();
    }

    if (!bookingCardRepository
        .existsBookingCardByBookingIdAndAndCustomerUserUsername(bookingId, username)) {
      throw new NotFoundException("booking-card-not-found");
    }

    List<ServiceDetail> serviceDetailList = serviceDetailRepository
        .findAllByBookingCardBookingId(bookingId);

    return serviceDetailList.stream()
        .map(detail -> serviceDetailMapper.mapToServiceDetailResponse(detail))
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public void deleteServiceDetail(ServiceDetailId serviceDetailId) {
    ServiceDetail serviceDetail = serviceDetailRepository.findById(serviceDetailId)
        .orElseThrow(() -> new NotFoundException("service-detail-not-found"));
    serviceDetailRepository.delete(serviceDetail);
    bookingCardService.updatePriceOfBookingCard(serviceDetailId.getBookingCard());
  }

  @Override
  public List<GetServiceResponse> getServiceBookingRank() {
    List<GetServiceResponse> serviceRank = new ArrayList<>();
    List<ServiceDetail> serviceDetailList = serviceDetailRepository.findAll();
    List<Services> serviceList = serviceRepository.findAll();
    HashMap<Services, Integer> getTimesServiceBooking = new HashMap<>();
    for (Services service : serviceList) {
      getTimesServiceBooking.put(service, 0);
    }

    for (ServiceDetail serviceDetail : serviceDetailList) {
      getTimesServiceBooking
          .put(serviceDetail.getService(),
              getTimesServiceBooking.get(serviceDetail.getService()) + 1);
    }
    List<Integer> listTempTimes = new ArrayList<>();
    for (Integer i : getTimesServiceBooking.values()) {
      listTempTimes.add(i);
    }
    Collections.sort(listTempTimes, new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o1 > o2 ? -1 : 1;
      }
    });

    for (Integer i : listTempTimes) {
      for (Services j : getTimesServiceBooking.keySet()) {
        if (i == getTimesServiceBooking.get(j)) {
          GetServiceResponse getServiceResponse = serviceMapper
              .mapToGetServiceResponse(j);
          if (!serviceRank.contains(getServiceResponse)) {
            serviceRank.add(getServiceResponse);
          }
        }
      }
    }
    return serviceRank;
  }

}
