package qlks_hdv.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import qlks_hdv.entity.RoomType;
import qlks_hdv.response.GetRoomTypeWithPriceResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-11T09:37:39+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15 (Oracle Corporation)"
)
@Component
public class RoomTypeMapperImpl implements RoomTypeMapper {

    @Override
    public GetRoomTypeWithPriceResponse mapToGetRoomTypeWithPriceResponse(RoomType roomType, Integer price) {
        if ( roomType == null && price == null ) {
            return null;
        }

        GetRoomTypeWithPriceResponse getRoomTypeWithPriceResponse = new GetRoomTypeWithPriceResponse();

        if ( roomType != null ) {
            getRoomTypeWithPriceResponse.setId( roomType.getId() );
            getRoomTypeWithPriceResponse.setNumberOfBed( roomType.getNumberOfBed() );
            getRoomTypeWithPriceResponse.setName( roomType.getName() );
            getRoomTypeWithPriceResponse.setDesciption( roomType.getDesciption() );
        }
        if ( price != null ) {
            getRoomTypeWithPriceResponse.setPrice( price );
        }

        return getRoomTypeWithPriceResponse;
    }
}
