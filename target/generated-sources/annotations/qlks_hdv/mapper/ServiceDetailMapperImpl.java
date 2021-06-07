package qlks_hdv.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import qlks_hdv.entity.BookingCard;
import qlks_hdv.entity.ServiceDetail;
import qlks_hdv.entity.Services;
import qlks_hdv.request.CreateServiceDetailRequest;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-07T12:34:52+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15 (Oracle Corporation)"
)
@Component
public class ServiceDetailMapperImpl implements ServiceDetailMapper {

    @Override
    public ServiceDetail mapToServiceDetail(CreateServiceDetailRequest createServiceDetailRequest, BookingCard bookingCard, Services service) {
        if ( createServiceDetailRequest == null && bookingCard == null && service == null ) {
            return null;
        }

        ServiceDetail serviceDetail = new ServiceDetail();

        if ( createServiceDetailRequest != null ) {
            serviceDetail.setPrice( createServiceDetailRequest.getPrice() );
            serviceDetail.setQuantity( createServiceDetailRequest.getQuantity() );
        }
        if ( bookingCard != null ) {
            serviceDetail.setBookingCard( bookingCard );
        }
        if ( service != null ) {
            serviceDetail.setService( service );
        }

        return serviceDetail;
    }
}
