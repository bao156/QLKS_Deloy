package qlks_hdv.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import qlks_hdv.entity.Staff;
import qlks_hdv.entity.User;
import qlks_hdv.request.CreateStaffRequest;
import qlks_hdv.request.UpdateStaffRequest;
import qlks_hdv.response.GetStaffResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-09T14:01:19+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15 (Oracle Corporation)"
)
@Component
public class StaffMapperImpl implements StaffMapper {

    @Override
    public Staff mapToStaff(CreateStaffRequest createStaffRequest, User user) {
        if ( createStaffRequest == null && user == null ) {
            return null;
        }

        Staff staff = new Staff();

        if ( createStaffRequest != null ) {
            staff.setFirstName( createStaffRequest.getFirstName() );
            staff.setLastName( createStaffRequest.getLastName() );
            staff.setPhone( createStaffRequest.getPhone() );
            staff.setEmail( createStaffRequest.getEmail() );
            staff.setAddress( createStaffRequest.getAddress() );
        }
        if ( user != null ) {
            staff.setUser( user );
        }

        return staff;
    }

    @Override
    public Staff mapToStaff(UpdateStaffRequest updateCustomerRequest, Staff staff) {
        if ( updateCustomerRequest == null ) {
            return null;
        }

        staff.setFirstName( updateCustomerRequest.getFirstName() );
        staff.setLastName( updateCustomerRequest.getLastName() );
        staff.setPhone( updateCustomerRequest.getPhone() );
        staff.setEmail( updateCustomerRequest.getEmail() );
        staff.setAddress( updateCustomerRequest.getAddress() );

        return staff;
    }

    @Override
    public GetStaffResponse mapToGetStaffResponse(Staff staff) {
        if ( staff == null ) {
            return null;
        }

        GetStaffResponse getStaffResponse = new GetStaffResponse();

        getStaffResponse.setUsername( staffUserUsername( staff ) );
        getStaffResponse.setId( staff.getId() );
        getStaffResponse.setFirstName( staff.getFirstName() );
        getStaffResponse.setLastName( staff.getLastName() );
        getStaffResponse.setPhone( staff.getPhone() );
        getStaffResponse.setEmail( staff.getEmail() );
        getStaffResponse.setAddress( staff.getAddress() );

        return getStaffResponse;
    }

    private String staffUserUsername(Staff staff) {
        if ( staff == null ) {
            return null;
        }
        User user = staff.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }
}
