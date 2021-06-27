package qlks_hdv.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import qlks_hdv.entity.BookingCard;
import qlks_hdv.entity.RentingDetail;
import qlks_hdv.entity.Room;
import qlks_hdv.entity.Staff;
import qlks_hdv.request.CreateRentingDetailRequest;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-27T01:22:41+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15 (Oracle Corporation)"
)
@Component
public class RentingMapperImpl implements RentingMapper {

    @Override
    public RentingDetail mapToRenting(CreateRentingDetailRequest createRentingDetailRequest, BookingCard bookingCard, Room room, Staff staff) {
        if ( createRentingDetailRequest == null && bookingCard == null && room == null && staff == null ) {
            return null;
        }

        RentingDetail rentingDetail = new RentingDetail();

        if ( createRentingDetailRequest != null ) {
            rentingDetail.setRentAt( createRentingDetailRequest.getRentAt() );
            rentingDetail.setBackAt( createRentingDetailRequest.getBackAt() );
        }
        if ( bookingCard != null ) {
            rentingDetail.setBookingCard( bookingCard );
        }
        if ( room != null ) {
            rentingDetail.setRoom( room );
        }
        if ( staff != null ) {
            rentingDetail.setStaff( staff );
        }

        return rentingDetail;
    }
}
