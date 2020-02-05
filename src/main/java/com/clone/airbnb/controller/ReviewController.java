package com.clone.airbnb.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.clone.airbnb.dto.ReviewDto;
import com.clone.airbnb.entity.Review;
import com.clone.airbnb.service.ReviewService;

@PreAuthorize("isAuthenticated()")
@Controller
@RequestMapping(path="/reviews")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	
	
	@GetMapping("/write/{reservation_id}")
	public String reviewWrite(Principal principal, Model model, @PathVariable("reservation_id") Integer reservationId) {
		if (!reviewService.isValid(reservationId, principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		model.addAttribute("review", new ReviewDto());
		return "reviews/write";
	}
	
	
	
	@PostMapping("/write/{reservation_id}")
	public String writeReview(Principal principal, Model model, @PathVariable("reservation_id") Integer reservationId
			, @Valid @ModelAttribute("review") ReviewDto dto, BindingResult result) {
		if (!reviewService.isValid(reservationId, principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		if (result.hasErrors()) {
			return "reviews/write";
		}
		
		Review review = dto.toOriginal();
		reviewService.write(review, reservationId);
		
		return "redirect:/reservations/detail/" + reservationId;
	}
	
}
