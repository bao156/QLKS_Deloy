package qlks_hdv.entity.compositekey;

import java.io.Serializable;

public class RentingCompositeKey implements Serializable {
	
	private Integer bookingcardId;
	private String roomCode;
	
	
	
	
	public RentingCompositeKey() {
		super();
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookingcardId == null) ? 0 : bookingcardId.hashCode());
		result = prime * result + ((roomCode == null) ? 0 : roomCode.hashCode());
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
		RentingCompositeKey other = (RentingCompositeKey) obj;
		if (bookingcardId == null) {
			if (other.bookingcardId != null)
				return false;
		} else if (!bookingcardId.equals(other.bookingcardId))
			return false;
		if (roomCode == null) {
			if (other.roomCode != null)
				return false;
		} else if (!roomCode.equals(other.roomCode))
			return false;
		return true;
	}


	public Integer getBookingcardId() {
		return bookingcardId;
	}
	public void setBookingcardId(Integer bookingcardId) {
		this.bookingcardId = bookingcardId;
	}
	public String getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	
	

}
