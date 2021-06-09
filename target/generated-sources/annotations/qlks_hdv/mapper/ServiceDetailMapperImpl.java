package qlks_hdv.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import qlks_hdv.entity.BookingCard;
import qlks_hdv.entity.ServiceDetail;
import qlks_hdv.entity.Services;
import qlks_hdv.request.CreateServiceDetailRequest;
import qlks_hdv.response.GetServiceDetailResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-09T14:01:18+0700",
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

    @Override
    public GetServiceDetailResponse mapToServiceDetailResponse(ServiceDetail serviceDetail) {
        if ( serviceDetail == null ) {
            return null;
        }

        GetServiceDetailResponse getServiceDetailResponse = new GetServiceDetailResponse();

        getServiceDetailResponse.setNameService( serviceDetailServiceServiceName( serviceDetail ) );
        getServiceDetailResponse.setQuantity( serviceDetail.getQuantity() );
        getServiceDetailResponse.setPrice( serviceDetail.getPrice() );

        return getServiceDetailResponse;
    }

    private String serviceDetailServiceServiceName(ServiceDetail serviceDetail) {
        if ( serviceDetail == null ) {
            return null;
        }
        Services service = serviceDetail.getService();
        if ( service == null ) {
            return null;
        }
        String serviceName = service.getServiceName();
        if ( serviceName == null ) {
            return null;
        }
        return serviceName;
    }
}
