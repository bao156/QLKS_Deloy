package qlks_hdv.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import qlks_hdv.entity.Services;
import qlks_hdv.request.CreateServiceRequest;
import qlks_hdv.request.UpdateServiceRequest;
import qlks_hdv.response.GetServiceResponse;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

  Services mapToServices(CreateServiceRequest createServiceRequest);

  Services mapToServices(UpdateServiceRequest updateServiceRequest,
      @MappingTarget Services services);

  GetServiceResponse mapToGetServiceResponse(Services services);


}
