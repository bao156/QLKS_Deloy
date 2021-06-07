package qlks_hdv.service;

import java.util.List;
import qlks_hdv.request.CreateServiceRequest;
import qlks_hdv.request.UpdateServiceRequest;
import qlks_hdv.response.GetServiceResponse;

public interface IService {

  void createService(CreateServiceRequest createServiceRequest);

  void updateService(UpdateServiceRequest updateServiceRequest, Integer serviceId);

  void deleteService(Integer serviceId);

  List<GetServiceResponse> getServices();

}
