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

import com.clone.airbnb.admin.entity.AdminFormEntity;
import com.clone.airbnb.admin.form.annotation.EntityForm;
import com.clone.airbnb.admin.form.annotation.IntegerForm;
import com.clone.airbnb.admin.form.annotation.JoinOneForm;
import com.clone.airbnb.admin.form.annotation.JoinOneTextForm;
import com.clone.airbnb.admin.form.annotation.TextAreaForm;
import com.clone.airbnb.entity.sup.DateTimeModel;

import lombok.Getter;
import lombok.Setter;

@EntityForm
@Entity
@Table(name = "review")
@Getter
@Setter
public class Review extends DateTimeModel implements AdminFormEntity<Review> {
	
	@Id
    @GeneratedValue
	private Integer id;
	
	
	@TextAreaForm(blank = false, maxlength = 100, placeholder = "리뷰 작성, 최대 100자까지")
	@Column(nullable = false)
	@NotBlank
	@Size(max = 100)
	private String review;
	
	
	@IntegerForm(blank = false, min = 0, max = 5, placeholder = "0 ~ 5 점까지")
	@Column(nullable = false)
	@NotNull
	@Min(value = 0)
	@Max(value = 5)
	private Integer accuracy;
	
	
	@IntegerForm(blank = false, min = 0, max = 5, placeholder = "0 ~ 5 점까지")
	@Column(nullable = false)
	@NotNull
	@Min(value = 0)
	@Max(value = 5)
	private Integer communication;
	
	
	@IntegerForm(blank = false, min = 0, max = 5, placeholder = "0 ~ 5 점까지")
	@Column(nullable = false)
	@NotNull
	@Min(value = 0)
	@Max(value = 5)
	private Integer cleaniness;
	
	
	@IntegerForm(blank = false, min = 0, max = 5, placeholder = "0 ~ 5 점까지")
	@Column(nullable = false)
	@NotNull
	@Min(value = 0)
	@Max(value = 5)
	private Integer location;
	
	
	@IntegerForm(blank = false, min = 0, max = 5, placeholder = "0 ~ 5 점까지")
	@Column(nullable = false)
	@NotNull
	@Min(value = 0)
	@Max(value = 5)
	private Integer checkIn;
	
	
	@IntegerForm(blank = false, min = 0, max = 5, placeholder = "0 ~ 5 점까지")
	@Column(nullable = false)
	@NotNull
	@Min(value = 0)
	@Max(value = 5)
	private Integer value;
	
	
	@JoinOneForm(blank = false, itemLabel = "username", itemValue="id", method="users", defaultOption = "------ Select User ------")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(referencedColumnName = "id", nullable = false)
	@NotNull(message = "유저가 존재하지 않습니다.")
	private User user;
	
	
	@JoinOneTextForm(field = "id", placeholder = "Room ID 입력", blank = false)
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(referencedColumnName = "id", nullable = false)
	@NotNull(message = "룸이 존재하지 않습니다.")
	private Room room;
	
	
	
	public double ratingAverage() {
		return ratingAverage(this.getAccuracy(), this.getCommunication(), this.getCleaniness()
				, this.getLocation(), this.getCheckIn(), this.getValue());
	}
	
	
	
	
	public static double ratingAverage(Integer accuracy, Integer communication, Integer cleaniness
			, Integer location, Integer checkIn, Integer value) {
		double avg = 
				(accuracy 
				+ communication 
				+ cleaniness
				+ location
				+ checkIn
				+ value) / 6.0;
		return (Math.round(avg * 100) / 100.0);
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
		if (t.getCreated()			!= null) this.setCreated(t.getCreated()); 
    	if (t.getUpdated()			!= null) this.setUpdated(t.getUpdated());
	}
	
	
	
	@Override
	public String toString() {
		return "Review[id=" + id + ",review=" + review 
				+ ",accuracy=" + accuracy + ",communication=" + communication 
				+ ",cleaniness=" + cleaniness + ",location=" + location 
				+ ",checkIn=" + checkIn + ",value=" + value
				+ ",user=" + (user != null ? user.getUsername() : null)
				+ ",room=" + (room != null ? room.getId() : null) + "]";
	}
	
}
