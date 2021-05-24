package qlks_hdv.service;

import java.util.List;
import qlks_hdv.request.CreateStaffRequest;
import qlks_hdv.request.UpdateStaffRequest;
import qlks_hdv.response.GetStaffResponse;

public interface IStaffService {

  void createStaff(CreateStaffRequest createStaffRequest);

  void updateStaff(UpdateStaffRequest updateStaffRequest, Integer staffId);

  void deleteStaff(Integer staffId);

  GetStaffResponse getGetStaffResponse(Integer staffId);

  List<GetStaffResponse> getStaffList();

}
