package qlks_hdv.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import qlks_hdv.entity.BookingCard;
import qlks_hdv.entity.Customer;
import qlks_hdv.entity.Discount;
import qlks_hdv.entity.User;
import qlks_hdv.request.CreateBookingCardRequest;
import qlks_hdv.response.GetBookingCardForPaymentReponse;
import qlks_hdv.response.GetBookingCardReponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-16T03:23:58+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15 (Oracle Corporation)"
)
@Component
public class BookingCardMapperImpl implements BookingCardMapper {

    @Override
    public BookingCard mapToBookingCard(CreateBookingCardRequest createBookingCardRequest, Integer price, String status, Customer customer, Discount discount) {
        if ( createBookingCardRequest == null && price == null && status == null && customer == null && discount == null ) {
            return null;
        }

        BookingCard bookingCard = new BookingCard();

        if ( price != null ) {
            bookingCard.setPrice( price );
        }
        if ( status != null ) {
            bookingCard.setStatus( status );
        }
        if ( customer != null ) {
            bookingCard.setCustomer( customer );
        }
        if ( discount != null ) {
            bookingCard.setDiscount( discount );
        }

        return bookingCard;
    }

    @Override
    public GetBookingCardReponse mapToGetBookingCardReponse(BookingCard bookingCard) {
        if ( bookingCard == null ) {
            return null;
        }

        GetBookingCardReponse getBookingCardReponse = new GetBookingCardReponse();

        getBookingCardReponse.setUsername( bookingCardCustomerUserUsername( bookingCard ) );
        getBookingCardReponse.setLastName( bookingCardCustomerLastName( bookingCard ) );
        getBookingCardReponse.setPhoneNumber( bookingCardCustomerPhone( bookingCard ) );
        getBookingCardReponse.setDiscountName( bookingCardDiscountDiscountName( bookingCard ) );
        getBookingCardReponse.setBookingId( bookingCard.getBookingId() );
        getBookingCardReponse.setPrice( bookingCard.getPrice() );
        getBookingCardReponse.setStatus( bookingCard.getStatus() );
        getBookingCardReponse.setCompleteAt( bookingCard.getCompleteAt() );

        return getBookingCardReponse;
    }

    @Override
    public GetBookingCardForPaymentReponse mapTGetBookingCardForPaymentReponse(BookingCard bookingCard, Integer serviceAmount, Integer roomAmount, String staffUser, Customer customer) {
        if ( bookingCard == null && serviceAmount == null && roomAmount == null && staffUser == null && customer == null ) {
            return null;
        }

        GetBookingCardForPaymentReponse getBookingCardForPaymentReponse = new GetBookingCardForPaymentReponse();

        if ( bookingCard != null ) {
            getBookingCardForPaymentReponse.setBookingId( bookingCard.getBookingId() );
            getBookingCardForPaymentReponse.setCompleteAt( bookingCard.getCompleteAt() );
        }
        if ( serviceAmount != null ) {
            getBookingCardForPaymentReponse.setServiceAmount( serviceAmount );
        }
        if ( roomAmount != null ) {
            getBookingCardForPaymentReponse.setRoomAmount( roomAmount );
        }
        if ( staffUser != null ) {
            getBookingCardForPaymentReponse.setUsername( staffUser );
        }
        if ( customer != null ) {
            getBookingCardForPaymentReponse.setFirstName( customer.getFirstName() );
            getBookingCardForPaymentReponse.setLastName( customer.getLastName() );
            getBookingCardForPaymentReponse.setCMND( customer.getCMND() );
        }

        return getBookingCardForPaymentReponse;
    }

    private String bookingCardCustomerUserUsername(BookingCard bookingCard) {
        if ( bookingCard == null ) {
            return null;
        }
        Customer customer = bookingCard.getCustomer();
        if ( customer == null ) {
            return null;
        }
        User user = customer.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }

    private String bookingCardCustomerLastName(BookingCard bookingCard) {
        if ( bookingCard == null ) {
            return null;
        }
        Customer customer = bookingCard.getCustomer();
        if ( customer == null ) {
            return null;
        }
        String lastName = customer.getLastName();
        if ( lastName == null ) {
            return null;
        }
        return lastName;
    }

    private String bookingCardCustomerPhone(BookingCard bookingCard) {
        if ( bookingCard == null ) {
            return null;
        }
        Customer customer = bookingCard.getCustomer();
        if ( customer == null ) {
            return null;
        }
        String phone = customer.getPhone();
        if ( phone == null ) {
            return null;
        }
        return phone;
    }

    private String bookingCardDiscountDiscountName(BookingCard bookingCard) {
        if ( bookingCard == null ) {
            return null;
        }
        Discount discount = bookingCard.getDiscount();
        if ( discount == null ) {
            return null;
        }
        String discountName = discount.getDiscountName();
        if ( discountName == null ) {
            return null;
        }
        return discountName;
    }
}
