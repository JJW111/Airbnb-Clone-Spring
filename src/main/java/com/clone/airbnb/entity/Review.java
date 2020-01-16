package com.clone.airbnb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.clone.airbnb.admin.entity.AdminFormEntity;
import com.clone.airbnb.admin.form.annotation.EntityForm;
import com.clone.airbnb.admin.form.annotation.IntegerForm;
import com.clone.airbnb.admin.form.annotation.JoinOneForm;
import com.clone.airbnb.admin.form.annotation.JoinOneTextForm;
import com.clone.airbnb.admin.form.annotation.TextAreaForm;
import com.clone.airbnb.dto.SafeUser;
import com.clone.airbnb.repository.UserRepository;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EntityForm
@Entity
@Table(name = "review")
@ToString(exclude = { "user", "room" })
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review implements AdminFormEntity<Review> {
	
	@Id
    @GeneratedValue
	private Integer id;
	
	
	@TextAreaForm(blank = false, maxlength = 100, placeholder = "리뷰 작성, 최대 100자까지")
	@Column(nullable = false)
	@Length(max = 100)
	private String review;
	
	
	@IntegerForm(blank = false, min = 0, max = 5, placeholder = "0 ~ 5 점까지")
	@Column(nullable = false)
	private Integer accuracy;
	
	
	@IntegerForm(blank = false, min = 0, max = 5, placeholder = "0 ~ 5 점까지")
	@Column(nullable = false)
	private Integer communication;
	
	
	@IntegerForm(blank = false, min = 0, max = 5, placeholder = "0 ~ 5 점까지")
	@Column(nullable = false)
	private Integer cleaniness;
	
	
	@IntegerForm(blank = false, min = 0, max = 5, placeholder = "0 ~ 5 점까지")
	@Column(nullable = false)
	private Integer location;
	
	
	@IntegerForm(blank = false, min = 0, max = 5, placeholder = "0 ~ 5 점까지")
	@Column(nullable = false)
	private Integer checkIn;
	
	
	@IntegerForm(blank = false, min = 0, max = 5, placeholder = "0 ~ 5 점까지")
	@Column(nullable = false)
	private Integer value;
	
	
	@JoinOneForm(blank = false, field = "username", repository = UserRepository.class, defaultOption = "------ Select User ------")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(referencedColumnName = "id", nullable = false)
	private User user;
	
	
	@JoinOneTextForm(field = "id", placeholder = "Room ID 입력", blank = false)
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(referencedColumnName = "id", nullable = false)
	private Room room;
	
	
	
	public Double ratingAverage() {
		double avg = 
				(this.accuracy 
				+ this.communication 
				+ this.cleaniness
				+ this.location
				+ this.checkIn
				+ this.value) / 6.0;
		return (Math.round(avg * 100) / 100.0);
	}
	
	
	
	@ToString(exclude = { "user", "room" })
	@Getter
	public static class Builder {
		private Integer id;
		@NotBlank
		@Size(max = 100)
		private String review;
		@NotNull
		@Min(value = 0)
		@Max(value = 5)
		private Integer accuracy;
		@NotNull
		@Min(value = 0)
		@Max(value = 5)
		private Integer communication;
		@NotNull
		@Min(value = 0)
		@Max(value = 5)
		private Integer cleaniness;
		@NotNull
		@Min(value = 0)
		@Max(value = 5)
		private Integer location;
		@NotNull
		@Min(value = 0)
		@Max(value = 5)
		private Integer checkIn;
		@NotNull
		@Min(value = 0)
		@Max(value = 5)
		private Integer value;
		@NotNull(message = "유저가 존재하지 않습니다.")
		private SafeUser user;
		@NotNull(message = "룸이 존재하지 않습니다.")
		private Room room;
		
		
		
		public Builder setId(Integer id) {
			this.id = id;
			return this;
		}
		
		
		
		public Builder setReview(String review) {
			this.review = review;
			return this;
		}
		
		
		
		public Builder setAccuracy(Integer accuracy) {
			this.accuracy = accuracy;
			return this;
		}
		
		
		
		public Builder setCommunication(Integer communication) {
			this.communication = communication;
			return this;
		}
		
		
		
		public Builder setCleaniness(Integer cleaniness) {
			this.cleaniness = cleaniness;
			return this;
		}
		
		
		
		public Builder setLocation(Integer location) {
			this.location = location;
			return this;
		}
		
		
		
		public Builder setCheckIn(Integer checkIn) {
			this.checkIn = checkIn;
			return this;
		}
		
		
		
		public Builder setValue(Integer value) {
			this.value = value;
			return this;
		}
		
		
		
		public Builder setUser(SafeUser user) {
			this.user = user;
			return this;
		}
		
		
		
		public Builder setRoom(Room room) {
			this.room = room;
			return this;
		}
		
		
		
		public Review build() {
			return new Review(this);
		}
		
	}
	
	
	
	public static Builder builder() {
		return new Builder();
	}
	
	
	
	private Review(Builder builder) {
		this.setId(builder.getId());
		this.setReview(builder.getReview());
		this.setAccuracy(builder.getAccuracy());
		this.setCommunication(builder.getCommunication());
		this.setCleaniness(builder.getCleaniness());
		this.setLocation(builder.getLocation());
		this.setCheckIn(builder.getCheckIn());
		this.setValue(builder.getValue());
		this.setUser(User.toUser(builder.getUser()));
		this.setRoom(builder.getRoom());
	}
	


	@Override
	public Review deepClone() {
		Review review = builder()
			.setId(this.getId())
			.setReview(this.getReview())
			.setAccuracy(this.getAccuracy())
			.setCommunication(this.getCommunication())
			.setCleaniness(this.getCleaniness())
			.setLocation(this.getLocation())
			.setCheckIn(this.getCheckIn())
			.setValue(this.getValue())
			.setRoom(this.getRoom())
			.build();
		review.setUser(this.getUser());
		
		return review;
	}


	@Override
	public void override(Review t) {
		if (t.getId() 				!= null) this.setId(t.getId());
		if (t.getReview()			!= null) this.setReview(t.getReview());
		if (t.getAccuracy()			!= null) this.setAccuracy(t.getAccuracy());
		if (t.getCommunication()	!= null) this.setCommunication(t.getCommunication());
		if (t.getCleaniness()		!= null) this.setCleaniness(t.getCleaniness());
		if (t.getLocation()			!= null) this.setLocation(t.getLocation());
		if (t.getCheckIn()			!= null) this.setCheckIn(t.getCheckIn());
		if (t.getValue()			!= null) this.setValue(t.getValue());
		if (t.getUser()				!= null) this.setUser(t.getUser());
		if (t.getRoom()				!= null) this.setRoom(t.getRoom());
	}
	
}
