package qlks_hdv.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import qlks_hdv.entity.Room;
import qlks_hdv.entity.RoomType;
import qlks_hdv.request.CreateRoomRequest;
import qlks_hdv.request.UpdateRoomRequest;
import qlks_hdv.response.GetRoomResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-24T12:10:59+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15 (Oracle Corporation)"
)
@Component
public class RoomMapperImpl implements RoomMapper {

    @Override
    public Room mapToRoom(CreateRoomRequest createRoomRequest, String status, RoomType type) {
        if ( createRoomRequest == null && status == null && type == null ) {
            return null;
        }

        Room room = new Room();

        if ( createRoomRequest != null ) {
            room.setRoomCode( createRoomRequest.getRoomCode() );
            room.setDescription( createRoomRequest.getDescription() );
            room.setImage( createRoomRequest.getImage() );
        }
        if ( status != null ) {
            room.setStatus( status );
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
        getRoomResponse.setStatus( room.getStatus() );
        getRoomResponse.setDescription( room.getDescription() );
        getRoomResponse.setImage( room.getImage() );

        return getRoomResponse;
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
