package com.clone.airbnb.dto.admin;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.clone.airbnb.entity.Review;
import com.clone.airbnb.entity.Room;
import com.clone.airbnb.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewDto implements DtoToOriginalSwitcher<Review> {
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
	private User user;
	
	
	@NotNull(message = "룸이 존재하지 않습니다.")
	private Room room;
	
	
	
	@Override
    public Review toOriginal() {
		Review review = new Review();
		review.setReview(this.review);
		review.setAccuracy(this.accuracy);
		review.setCheckIn(this.checkIn);
		review.setCleaniness(this.getCleaniness());
		review.setCommunication(this.getCommunication());
		review.setLocation(this.getLocation());
		review.setValue(this.getValue());
		review.setRoom(this.getRoom());
		review.setUser(this.getUser());

		return review;
    }
}
