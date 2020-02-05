package com.clone.airbnb.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.clone.airbnb.entity.Review;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {
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
	
	
	
	public Review toOriginal() {
		Review review = new Review();
		review.setReview(this.review);
		review.setAccuracy(accuracy);
		review.setCommunication(communication);
		review.setCleaniness(cleaniness);
		review.setLocation(location);
		review.setCheckIn(checkIn);
		review.setValue(value);
		return review;
	}
}
