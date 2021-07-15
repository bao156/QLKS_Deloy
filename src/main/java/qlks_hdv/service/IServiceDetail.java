package qlks_hdv.service;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import qlks_hdv.entity.compositekey.ServiceDetailId;
import qlks_hdv.request.CreateServiceDetailRequest;
import qlks_hdv.response.GetServiceDetailResponse;
import qlks_hdv.response.GetServiceResponse;

public interface IServiceDetail {

  void addServiceDetail(CreateServiceDetailRequest createServiceDetailRequest);

  List<GetServiceDetailResponse> getServiceDetailResponseList(Integer bookingId, String username);

  void deleteServiceDetail(ServiceDetailId serviceDetailId);

  List<GetServiceResponse> getServiceBookingRank();


}
