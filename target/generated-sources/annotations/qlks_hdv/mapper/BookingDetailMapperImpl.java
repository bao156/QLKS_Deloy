package qlks_hdv.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import qlks_hdv.entity.BookingCard;
import qlks_hdv.entity.BookingDetail;
import qlks_hdv.entity.RoomType;
import qlks_hdv.request.CreateBookingDetailRequest;
import qlks_hdv.response.GetBookingDetailResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-15T13:17:19+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15 (Oracle Corporation)"
)
@Component
public class BookingDetailMapperImpl implements BookingDetailMapper {

    @Override
    public BookingDetail mapToBookingDetail(CreateBookingDetailRequest createBookingDetailRequest, BookingCard bookingCard, RoomType roomType) {
        if ( createBookingDetailRequest == null && bookingCard == null && roomType == null ) {
            return null;
        }

        BookingDetail bookingDetail = new BookingDetail();

        if ( createBookingDetailRequest != null ) {
            bookingDetail.setRecieveAt( createBookingDetailRequest.getRecieveAt() );
            bookingDetail.setBackAt( createBookingDetailRequest.getBackAt() );
            bookingDetail.setAmount( createBookingDetailRequest.getAmount() );
        }
        if ( bookingCard != null ) {
            bookingDetail.setBookingCard( bookingCard );
        }
        if ( roomType != null ) {
            bookingDetail.setType( roomType );
        }

        return bookingDetail;
    }

    @Override
    public GetBookingDetailResponse mapToGetBookingDetailResponse(BookingDetail bookingDetail) {
        if ( bookingDetail == null ) {
            return null;
        }

        GetBookingDetailResponse getBookingDetailResponse = new GetBookingDetailResponse();

        getBookingDetailResponse.setBookingId( bookingDetailBookingCardBookingId( bookingDetail ) );
        getBookingDetailResponse.setNameType( bookingDetailTypeName( bookingDetail ) );
        getBookingDetailResponse.setTypeId( bookingDetailTypeId( bookingDetail ) );
        getBookingDetailResponse.setNumberOfBed( bookingDetailTypeNumberOfBed( bookingDetail ) );
        getBookingDetailResponse.setRecieveAt( bookingDetail.getRecieveAt() );
        getBookingDetailResponse.setBackAt( bookingDetail.getBackAt() );
        getBookingDetailResponse.setAmount( bookingDetail.getAmount() );
        getBookingDetailResponse.setPrice( bookingDetail.getPrice() );

        return getBookingDetailResponse;
    }

    private Integer bookingDetailBookingCardBookingId(BookingDetail bookingDetail) {
        if ( bookingDetail == null ) {
            return null;
        }
        BookingCard bookingCard = bookingDetail.getBookingCard();
        if ( bookingCard == null ) {
            return null;
        }
        Integer bookingId = bookingCard.getBookingId();
        if ( bookingId == null ) {
            return null;
        }
        return bookingId;
    }

    private String bookingDetailTypeName(BookingDetail bookingDetail) {
        if ( bookingDetail == null ) {
            return null;
        }
        RoomType type = bookingDetail.getType();
        if ( type == null ) {
            return null;
        }
        String name = type.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Integer bookingDetailTypeId(BookingDetail bookingDetail) {
        if ( bookingDetail == null ) {
            return null;
        }
        RoomType type = bookingDetail.getType();
        if ( type == null ) {
            return null;
        }
        Integer id = type.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer bookingDetailTypeNumberOfBed(BookingDetail bookingDetail) {
        if ( bookingDetail == null ) {
            return null;
        }
        RoomType type = bookingDetail.getType();
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
