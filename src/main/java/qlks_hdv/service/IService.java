package qlks_hdv.service;

import qlks_hdv.request.CreateServiceRequest;
import qlks_hdv.request.UpdateServiceRequest;

public interface IService {

  void createService(CreateServiceRequest createServiceRequest);

  void updateService(UpdateServiceRequest updateServiceRequest, Integer serviceId);

  void deleteService(Integer serviceId);

}
