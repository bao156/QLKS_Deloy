package qlks_hdv.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import qlks_hdv.entity.Room;
import qlks_hdv.entity.RoomType;
import qlks_hdv.request.CreateRoomRequest;
import qlks_hdv.request.UpdateRoomRequest;
import qlks_hdv.response.GetRoomResponse;
import qlks_hdv.response.GetRoomResponseWithPrice;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-09T21:50:31+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15 (Oracle Corporation)"
)
@Component
public class RoomMapperImpl implements RoomMapper {

    @Override
    public Room mapToRoom(CreateRoomRequest createRoomRequest, RoomType type) {
        if ( createRoomRequest == null && type == null ) {
            return null;
        }

        Room room = new Room();

        if ( createRoomRequest != null ) {
            room.setRoomCode( createRoomRequest.getRoomCode() );
            room.setDescription( createRoomRequest.getDescription() );
            room.setImage( createRoomRequest.getImage() );
        }
        if ( type != null ) {
            room.setType( type );
        }

        return room;
    }

    @Override
    public Room mapToRoom(UpdateRoomRequest updateRoomRequest, Room room) {
        if ( updateRoomRequest == null ) {
            return null;
        }

        room.setDescription( updateRoomRequest.getDescription() );
        room.setImage( updateRoomRequest.getImage() );

        return room;
    }

    @Override
    public GetRoomResponse mapToGetRoomResponse(Room room) {
        if ( room == null ) {
            return null;
        }

        GetRoomResponse getRoomResponse = new GetRoomResponse();

        getRoomResponse.setNumberOfBed( roomTypeNumberOfBed( room ) );
        getRoomResponse.setRoomCode( room.getRoomCode() );
        getRoomResponse.setDescription( room.getDescription() );
        getRoomResponse.setImage( room.getImage() );

        return getRoomResponse;
    }

    @Override
    public GetRoomResponseWithPrice mapToGetRoomResponseWithPrice(Room room, Integer price) {
        if ( room == null && price == null ) {
            return null;
        }

        GetRoomResponseWithPrice getRoomResponseWithPrice = new GetRoomResponseWithPrice();

        if ( room != null ) {
            getRoomResponseWithPrice.setNumberOfBed( roomTypeNumberOfBed( room ) );
            getRoomResponseWithPrice.setRoomCode( room.getRoomCode() );
            getRoomResponseWithPrice.setDescription( room.getDescription() );
            getRoomResponseWithPrice.setImage( room.getImage() );
        }
        if ( price != null ) {
            getRoomResponseWithPrice.setPrice( price );
        }

        return getRoomResponseWithPrice;
    }

    private Integer roomTypeNumberOfBed(Room room) {
        if ( room == null ) {
            return null;
        }
        RoomType type = room.getType();
        if ( type == null ) {
            return null;
        }
        Integer numberOfBed = type.getNumberOfBed();
        if ( numberOfBed == null ) {
            return null;
        }
        return numberOfBed;
    }
}
