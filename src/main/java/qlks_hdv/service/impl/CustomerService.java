package qlks_hdv.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import qlks_hdv.entity.Customer;
import qlks_hdv.entity.User;
import qlks_hdv.exception.ConflictException;
import qlks_hdv.exception.NotFoundException;
import qlks_hdv.mapper.CustomerMapper;
import qlks_hdv.repository.BookingCardRepository;
import qlks_hdv.repository.CustomerRepository;
import qlks_hdv.repository.UserRepository;
import qlks_hdv.request.CreateCustomerRequest;
import qlks_hdv.request.UpdateCustomerRequest;
import qlks_hdv.response.GetCustomerResponse;
import qlks_hdv.service.ICustomerService;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

  private final UserRepository userRepository;

  private final CustomerRepository customerRepository;

  private final CustomerMapper customerMapper;
  private final BookingCardRepository bookingCardRepository;

  @Override
  @Transactional
  public void createCustomer(CreateCustomerRequest createCustomerRequest) {

    User user = userRepository.findByUsername(createCustomerRequest.getUsername())
        .orElseThrow(() -> new NotFoundException("user-not-found"));

    if (customerRepository.existsByUserUsername(createCustomerRequest.getUsername())) {
      throw new ConflictException("username-already-exist");
    }
    if (customerRepository.existsByCMND(createCustomerRequest.getCMND())) {
      throw new ConflictException("cmnd-already-exist");
    }
    Customer customer = customerMapper.mapToCustomer(createCustomerRequest, user);
    customerRepository.save(customer);

  }

  @Override
  @Transactional
  public void updateCustomer(UpdateCustomerRequest updateCustomerRequest, Integer customerId) {

    Customer customer = customerRepository.findById(customerId)
        .orElseThrow(() -> new NotFoundException("user-not-found"));
    customer = customerMapper.mapToCustomer(updateCustomerRequest, customer);


  }

  @Override
  @Transactional
  public void deleteCustomer(Integer customerId) {
    if (!customerRepository.existsById(customerId)) {
      throw new NotFoundException("customer-not-found");
    }
    if (bookingCardRepository.existsByCustomerId(customerId)) {
      Customer customer = customerRepository.findById(customerId)
          .orElseThrow(() -> new NotFoundException("customer-not-found"));
    }
    customerRepository.deleteById(customerId);
  }

  @Override
  public GetCustomerResponse getCustomerByUsername(String username) {

    Customer customer = customerRepository.findByUserUsername(username)
        .orElseThrow(() -> new NotFoundException("customer-not-found"));
    return customerMapper.mapToGetCustomerResponse(customer, customer.getUser().getUsername());

  }

  @Override
  public List<GetCustomerResponse> getCustomerResponseList() {
    List<Customer> customerList = customerRepository.findAllBy()
        .orElseThrow(() -> new NotFoundException("list-be-empty"));
    List<GetCustomerResponse> getCustomerReponseList = customerList.stream()
        .map(develop -> {
          String username = "";
          if (develop.getUser() != null) {
            username = develop.getUser().getUsername();
          }
          return customerMapper
              .mapToGetCustomerResponse(develop, username);
        })
        .collect(Collectors.toList());
    return getCustomerReponseList;
  }

}
