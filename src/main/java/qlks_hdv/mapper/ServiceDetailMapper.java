package qlks_hdv.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import qlks_hdv.entity.BookingCard;
import qlks_hdv.entity.ServiceDetail;
import qlks_hdv.entity.Services;
import qlks_hdv.request.CreateServiceDetailRequest;
import qlks_hdv.response.GetServiceDetailResponse;

@Mapper(componentModel = "spring")
public interface ServiceDetailMapper {

  @Mappings({
      @Mapping(target = "price", source = "createServiceDetailRequest.price")
  })
  ServiceDetail mapToServiceDetail(CreateServiceDetailRequest createServiceDetailRequest,
      BookingCard bookingCard, Services service);

  @Mapping(target = "nameService", source = "serviceDetail.service.serviceName")
  GetServiceDetailResponse mapToServiceDetailResponse(ServiceDetail serviceDetail);
}
