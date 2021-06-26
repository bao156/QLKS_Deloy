package qlks_hdv.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import qlks_hdv.entity.Customer;
import qlks_hdv.entity.User;
import qlks_hdv.request.CreateCustomerRentingRequest;
import qlks_hdv.request.CreateCustomerRequest;
import qlks_hdv.request.UpdateCustomerRequest;
import qlks_hdv.response.GetCustomerResponse;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

  @Mapping(target = "user", source = "user")
  Customer mapToCustomer(CreateCustomerRequest createCustomerRequest, User user);

  Customer mapToCustomer(UpdateCustomerRequest updateCustomerRequest,
      @MappingTarget Customer customer);

  @Mapping(target = "username", source = "username")
  GetCustomerResponse mapToGetCustomerResponse(Customer customer, String username);

  Customer mapToCustomer(CreateCustomerRentingRequest createCustomerRentingRequest);

}
