package com.clone.airbnb.dto.admin;

import java.util.Date;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.clone.airbnb.common.Common;
import com.clone.airbnb.entity.Reservation;
import com.clone.airbnb.entity.Room;
import com.clone.airbnb.entity.User;
import com.clone.airbnb.entity.enu.ReservationStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReservationDto implements DtoToOriginalSwitcher<Reservation> {
	@NotNull
	private ReservationStatus status = ReservationStatus.PENDING;
	@DateTimeFormat(pattern = Common.DATE_FORMAT)
	@NotNull
	private Date checkIn;
	@DateTimeFormat(pattern = Common.DATE_FORMAT)
	@NotNull
	private Date checkOut;
	@NotNull(message = "게스트가 존재하지 않습니다.")
	private User guest;
	@NotNull(message = "룸이 존재하지 않습니다.")
	private Room room;
	
	@Override
    public Reservation toOriginal() {
		Reservation reservation = new Reservation();
		reservation.setStatus(this.status);
		reservation.setCheckIn(this.checkIn);
		reservation.setCheckOut(this.checkOut);
		reservation.setGuest(this.guest);
		reservation.setRoom(this.room);

		return reservation;
    }
}
