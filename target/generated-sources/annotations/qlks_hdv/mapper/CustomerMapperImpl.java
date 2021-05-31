package qlks_hdv.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import qlks_hdv.entity.Customer;
import qlks_hdv.entity.User;
import qlks_hdv.request.CreateCustomerRequest;
import qlks_hdv.request.UpdateCustomerRequest;
import qlks_hdv.response.GetCustomerResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-29T15:19:38+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer mapToCustomer(CreateCustomerRequest createCustomerRequest, User user) {
        if ( createCustomerRequest == null && user == null ) {
            return null;
        }

        Customer customer = new Customer();

        if ( createCustomerRequest != null ) {
            customer.setFirstName( createCustomerRequest.getFirstName() );
            customer.setLastName( createCustomerRequest.getLastName() );
            customer.setPhone( createCustomerRequest.getPhone() );
            customer.setEmail( createCustomerRequest.getEmail() );
        }
        if ( user != null ) {
            customer.setUser( user );
        }

        return customer;
    }

    @Override
    public Customer mapToCustomer(UpdateCustomerRequest updateCustomerRequest, Customer customer) {
        if ( updateCustomerRequest == null ) {
            return null;
        }

        customer.setFirstName( updateCustomerRequest.getFirstName() );
        customer.setLastName( updateCustomerRequest.getLastName() );
        customer.setPhone( updateCustomerRequest.getPhone() );
        customer.setEmail( updateCustomerRequest.getEmail() );

        return customer;
    }

    @Override
    public GetCustomerResponse mapToGetCustomerResponse(Customer customer, String username) {
        if ( customer == null && username == null ) {
            return null;
        }

        GetCustomerResponse getCustomerResponse = new GetCustomerResponse();

        if ( customer != null ) {
            getCustomerResponse.setFirstName( customer.getFirstName() );
            getCustomerResponse.setLastName( customer.getLastName() );
            getCustomerResponse.setPhone( customer.getPhone() );
            getCustomerResponse.setEmail( customer.getEmail() );
        }
        if ( username != null ) {
            getCustomerResponse.setUsername( username );
        }

        return getCustomerResponse;
    }
}
