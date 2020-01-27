package com.clone.airbnb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import com.clone.airbnb.admin.entity.AdminFormEntity;
import com.clone.airbnb.admin.form.annotation.DatetimeForm;
import com.clone.airbnb.admin.form.annotation.EntityForm;
import com.clone.airbnb.admin.form.annotation.JoinOneForm;
import com.clone.airbnb.admin.form.annotation.JoinOneTextForm;
import com.clone.airbnb.admin.form.annotation.SelectBoxForm;
import com.clone.airbnb.common.Common;
import com.clone.airbnb.entity.enu.ReservationStatus;

import lombok.Getter;
import lombok.Setter;

@EntityForm
@Entity
@Table(name = "reservation")
@Getter
@Setter
public class Reservation implements AdminFormEntity<Reservation> {
	
	@Id
    @GeneratedValue
	private Integer id;
	
	
	
	@SelectBoxForm(blank = false, defaultOption="예약 상태 선택")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
	@NotNull
	private ReservationStatus status = ReservationStatus.PENDING;
	
	
	
	@DatetimeForm(blank = false)
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = Common.DATETIME_FORMAT)
	@NotNull
	private Date checkIn;
	
	
	
	@DatetimeForm(blank = false)
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = Common.DATETIME_FORMAT)
	@NotNull
	private Date checkOut;
	
	
	
	@JoinOneForm(blank = false, itemLabel = "username", itemValue="id", method="users", defaultOption = "------ Select User ------")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(referencedColumnName = "id", nullable = false)
	@NotNull(message = "게스트가 존재하지 않습니다.")
	private User guest;
	
	
	
	@JoinOneTextForm(field = "id", placeholder = "Room ID 입력", blank = false)
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(referencedColumnName = "id", nullable = false)
	@NotNull(message = "룸이 존재하지 않습니다.")
	private Room room;
	
	
	
	@Override
	public void override(Reservation t) {
		if (t.getId() 				!= null) this.setId(t.getId());
		if (t.getStatus()			!= null) this.setStatus(t.getStatus());
		if (t.getCheckIn()			!= null) this.setCheckIn(t.getCheckIn());
		if (t.getCheckOut()			!= null) this.setCheckOut(t.getCheckOut());
		if (t.getGuest()			!= null) this.setGuest(t.getGuest());
		if (t.getRoom()				!= null) this.setRoom(t.getRoom());
	}
	
	
	@Override
	public String toString() {
		return "Reservation[id=" + id + ",status=" + status 
				+ ",checkIn=" + checkIn + ",checkOut=" + checkOut 
				+ ",guest=" + (guest != null ? guest.getUsername() : null) + "]"
				+ ",room=" + (room != null ? room.getId() : null) + "]";
	}
	
}
