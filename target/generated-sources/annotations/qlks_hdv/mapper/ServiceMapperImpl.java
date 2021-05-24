package qlks_hdv.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import qlks_hdv.entity.Services;
import qlks_hdv.request.CreateServiceRequest;
import qlks_hdv.request.UpdateServiceRequest;
import qlks_hdv.response.GetServiceResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-24T16:16:41+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15 (Oracle Corporation)"
)
@Component
public class ServiceMapperImpl implements ServiceMapper {

    @Override
    public Services mapToServices(CreateServiceRequest createServiceRequest) {
        if ( createServiceRequest == null ) {
            return null;
        }

        Services services = new Services();

        services.setServiceName( createServiceRequest.getServiceName() );
        services.setPrice( createServiceRequest.getPrice() );
        services.setImage( createServiceRequest.getImage() );

        return services;
    }

    @Override
    public Services mapToServices(UpdateServiceRequest updateServiceRequest, Services services) {
        if ( updateServiceRequest == null ) {
            return null;
        }

        services.setServiceName( updateServiceRequest.getServiceName() );
        services.setPrice( updateServiceRequest.getPrice() );
        services.setImage( updateServiceRequest.getImage() );

        return services;
    }

    @Override
    public GetServiceResponse mapToGetServiceResponse(Services services) {
        if ( services == null ) {
            return null;
        }

        GetServiceResponse getServiceResponse = new GetServiceResponse();

        getServiceResponse.setServiceName( services.getServiceName() );
        getServiceResponse.setPrice( services.getPrice() );
        getServiceResponse.setImage( services.getImage() );

        return getServiceResponse;
    }
}
