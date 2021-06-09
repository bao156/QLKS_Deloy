package qlks_hdv.service;

import java.util.List;
import qlks_hdv.entity.compositekey.ServiceDetailId;
import qlks_hdv.request.CreateServiceDetailRequest;
import qlks_hdv.response.GetServiceDetailResponse;

public interface IServiceDetail {

  void addServiceDetail(CreateServiceDetailRequest createServiceDetailRequest);

  List<GetServiceDetailResponse> getServiceDetailResponseList(Integer bookingId, String username);

  void deleteServiceDetail(ServiceDetailId serviceDetailId);

}
