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
import com.clone.airbnb.admin.form.annotation.JoinOneTextForm;
import com.clone.airbnb.admin.form.annotation.SelectBoxForm;
import com.clone.airbnb.common.Common;
import com.clone.airbnb.dto.SafeUser;
import com.clone.airbnb.entity.enu.ReservationStatus;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EntityForm
@Entity
@Table(name = "reservation")
@ToString(exclude = { "guest", "room" })
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation implements AdminFormEntity<Reservation> {
	
	@Id
    @GeneratedValue
	private Integer id;
	
	
	
	@SelectBoxForm(blank = false, defaultOption="예약 상태 선택")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
	private ReservationStatus status;
	
	
	
	@DatetimeForm(blank = false)
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date checkIn;
	
	
	
	@DatetimeForm(blank = false)
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date checkOut;
	
	
	
	@JoinOneTextForm(field = "username", placeholder = "게스트 USERNAME 입력", blank = false)
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(referencedColumnName = "id", nullable = false)
	private User guest;
	
	
	
	@JoinOneTextForm(field = "id", placeholder = "Room ID 입력", blank = false)
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(referencedColumnName = "id", nullable = false)
	private Room room;
	
	
	

	@ToString(exclude = { "guest", "room" })
	@Getter
	public static class Builder {
		private Integer id;
		@NotNull
		private ReservationStatus status;
		@DateTimeFormat(pattern = Common.DATETIME_FORMAT)
    	@NotNull
		private Date checkIn;
		@DateTimeFormat(pattern = Common.DATETIME_FORMAT)
    	@NotNull
		private Date checkOut;
		@NotNull(message = "게스트가 존재하지 않습니다.")
		private SafeUser guest;
		@NotNull(message = "룸이 존재하지 않습니다.")
		private Room room;
		
		
		
		public Builder setId(Integer id) {
			this.id = id;
			return this;
		}
		
		
		
		public Builder setStatus(ReservationStatus status) {
			this.status = status;
			return this;
		}
		
		
		
		public Builder setCheckIn(Date checkIn) {
			this.checkIn = checkIn;
			return this;
		}
		
		
		
		public Builder setCheckOut(Date checkOut) {
			this.checkOut = checkOut;
			return this;
		}
		
		
		
		public Builder setGuest(SafeUser guest) {
			this.guest = guest;
			return this;
		}
		
		
		
		public Builder setRoom(Room room) {
			this.room = room;
			return this;
		}
		
		
		
		public Reservation build() {
			return new Reservation(this);
		}
		
	}
	
	
	
	private Reservation(Builder builder) {
		this.setId(builder.getId());
		this.setStatus(builder.getStatus());
		this.setCheckIn(builder.getCheckIn());
		this.setCheckOut(builder.getCheckOut());
		this.setGuest(User.toUser(builder.getGuest()));
		this.setRoom(builder.getRoom());
	}
	
	
	
	public static Builder builder() {
		return new Builder();
	}
	
	
	
	public Builder toBuilder() {
		SafeUser safeUser = null;
		
		if (this.getGuest() != null) {
			safeUser = this.getGuest().toSafeUser();
		}
		
		return builder()
				.setId(this.getId())
				.setStatus(this.getStatus())
				.setCheckIn(this.getCheckIn())
				.setCheckOut(this.getCheckOut())
				.setRoom(this.getRoom())
				.setGuest(safeUser);
	}

	

	@Override
	public Reservation deepClone() {
		return this.toBuilder().build();
	}
	
	


	@Override
	public void override(Reservation t) {
		if (t.getId() 				!= null) this.setId(t.getId());
		if (t.getStatus()			!= null) this.setStatus(t.getStatus());
		if (t.getCheckIn()			!= null) this.setCheckIn(t.getCheckIn());
		if (t.getCheckOut()			!= null) this.setCheckOut(t.getCheckOut());
		if (t.getGuest()			!= null) this.setGuest(t.getGuest());
		if (t.getRoom()				!= null) this.setRoom(t.getRoom());
	}
	
}
