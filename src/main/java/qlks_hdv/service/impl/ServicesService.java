package qlks_hdv.service.impl;

import javax.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import qlks_hdv.entity.Services;
import qlks_hdv.exception.ConflictException;
import qlks_hdv.exception.NotFoundException;
import qlks_hdv.mapper.ServiceMapper;
import qlks_hdv.repository.ServiceRepository;
import qlks_hdv.request.CreateServiceRequest;
import qlks_hdv.request.UpdateServiceRequest;
import qlks_hdv.service.IService;

@Data
@RequiredArgsConstructor
@Service
public class ServicesService implements IService {


  private final ServiceMapper serviceMapper;

  private final ServiceRepository serviceRepository;

  @Override
  @Transactional
  public void createService(CreateServiceRequest createServiceRequest) {
    if (serviceRepository.existsByServiceName(createServiceRequest.getServiceName())) {
      throw new ConflictException("service-name-already-exist");
    }
    Services service = serviceMapper.mapToServices(createServiceRequest);
    serviceRepository.save(service);
  }

  @Override
  @Transactional
  public void updateService(UpdateServiceRequest updateServiceRequest, Integer serviceId) {

    Services service = serviceRepository.findById(serviceId)
        .orElseThrow(() -> new NotFoundException("service-not-found"));
    if (serviceRepository.existsByServiceName(updateServiceRequest.getServiceName()) && !service
        .getServiceName().equals(updateServiceRequest.getServiceName())) {
      throw new ConflictException("service-name-already-exist");
    }
    service = serviceMapper.mapToServices(updateServiceRequest, service);
    serviceRepository.save(service);

  }

  @Override
  @Transactional
  public void deleteService(Integer serviceId) {
    Services service = serviceRepository.findById(serviceId)
        .orElseThrow(() -> new NotFoundException("service-not-found"));
    serviceRepository.deleteById(serviceId);
  }

}
