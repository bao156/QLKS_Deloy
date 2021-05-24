package qlks_hdv.service;

import qlks_hdv.request.CreateCustomerRequest;
import qlks_hdv.request.UpdateCustomerRequest;
import qlks_hdv.response.GetCustomerResponse;

public interface ICustomerService {

  void createCustomer(CreateCustomerRequest createCustomerRequest);

  void updateCustomer(UpdateCustomerRequest updateCustomerRequest, Integer customerId);

  void deleteCustomer(Integer customerId);

  GetCustomerResponse getCustomerByUsername(String username);

  GetCustomerResponse getCustomerByCustomerId(Integer customerId);


}
