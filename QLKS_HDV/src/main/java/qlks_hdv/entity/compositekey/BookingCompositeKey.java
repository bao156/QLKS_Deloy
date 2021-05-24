package qlks_hdv.entity.compositekey;

import java.io.Serializable;

public class BookingCompositeKey implements Serializable{
	private Integer bookingcardId;
	private Integer typeId;
	
	
	public BookingCompositeKey() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookingcardId == null) ? 0 : bookingcardId.hashCode());
		result = prime * result + ((typeId == null) ? 0 : typeId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookingCompositeKey other = (BookingCompositeKey) obj;
		if (bookingcardId == null) {
			if (other.bookingcardId != null)
				return false;
		} else if (!bookingcardId.equals(other.bookingcardId))
			return false;
		if (typeId == null) {
			if (other.typeId != null)
				return false;
		} else if (!typeId.equals(other.typeId))
			return false;
		return true;
	}
	public Integer getBookingcardId() {
		return bookingcardId;
	}
	public void setBookingcardId(Integer bookingcardId) {
		this.bookingcardId = bookingcardId;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	


}
