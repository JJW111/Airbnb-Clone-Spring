package com.clone.airbnb.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clone.airbnb.entity.Reservation;
import com.clone.airbnb.entity.Review;
import com.clone.airbnb.entity.enu.ReservationStatus;
import com.clone.airbnb.exception.DataDoesNotExistsException;
import com.clone.airbnb.repository.ReservationRepository;
import com.clone.airbnb.repository.ReviewRepository;
import com.clone.airbnb.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	
	
	@Override
	public boolean isValid(Integer reservationId, String username) {
		Optional<Reservation> opt = reservationRepository.findById(reservationId);
		
		if (opt.isPresent()) {
			Reservation reservation = opt.get();
			
			if (!reservation.getGuest().getUsername().equals(username)
				|| !reservation.getStatus().equals(ReservationStatus.CONFIRMED)
				|| !reservation.isFinished()) {
				return false;
			}
			
			return true;
			
		} else {
			return false;
		}
	}
	
	
	
	@Override
	public void write(Review review, Integer reservationId) {
		Optional<Reservation> opt = reservationRepository.findById(reservationId);
		
		if (opt.isPresent()) {
			Reservation reservation = opt.get();
			review.setUser(reservation.getGuest());
			review.setRoom(reservation.getRoom());
			reviewRepository.save(review);
			
			reservation.setStatus(ReservationStatus.REVIEWED);
			reservationRepository.save(reservation);
		} else {
			throw new DataDoesNotExistsException("예약이 존재하지 않습니다.");
		}
	}
	
}
