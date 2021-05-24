package qlks_hdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qlks_hdv.entity.BookingDetail;
import qlks_hdv.entity.compositekey.BookingCompositeKey;

public interface BookingRepository extends JpaRepository<BookingDetail, BookingCompositeKey> {

}
