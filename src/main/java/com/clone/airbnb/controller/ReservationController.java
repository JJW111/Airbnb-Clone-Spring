package com.clone.airbnb.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clone.airbnb.entity.Reservation;
import com.clone.airbnb.exception.AlreadyReservedException;
import com.clone.airbnb.exception.DataDoesNotExistsException;
import com.clone.airbnb.exception.IsPastDateException;
import com.clone.airbnb.messages.RedirectMessageSystem;
import com.clone.airbnb.service.ReservationService;

@PreAuthorize("isAuthenticated()")
@Controller
@RequestMapping(path="/reservations")
public class ReservationController {
	
	private final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private ReservationService reservationService;
	
	
	
	
	@GetMapping("/manage")
	public String manageReservation(Principal principal, Model model) {
		List<Reservation> reservations = reservationService.listOfHost(principal.getName());
		model.addAttribute("reservations", reservations);
		return "reservations/manage_list";
	}
	
	
	
	
	@GetMapping("/my")
	public String reservations(Principal principal, Model model) {
		List<Reservation> reservations = reservationService.listOfGuest(principal.getName());
		model.addAttribute("reservations", reservations);
		return "reservations/list";
	}
	
	
	
	
	@PostMapping("/check-in")
	public String reserve(Principal principal, @RequestParam Map<String, String> body, RedirectAttributes redirectAttr) {
		Integer roomId = Integer.parseInt(body.get("room_id"));
		
		try {
			Date checkIn = format.parse(body.get("check_in"));
			Date checkOut = format.parse(body.get("check_out"));
			
			Reservation reservation = null;
					
			try {
				reservation = reservationService.reserve(principal.getName(), roomId, checkIn, checkOut);
				return "redirect:/reservations/detail/" + reservation.getId();
			} catch (IsPastDateException e) {
				RedirectMessageSystem.builder(redirectAttr)
					.warning("지난 날짜입니다.")
					.build();
			} catch (AlreadyReservedException e) {
				RedirectMessageSystem.builder(redirectAttr)
					.warning("이미 예약된 날짜입니다.")
					.build();
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
			
		} catch (ParseException e) {
			RedirectMessageSystem.builder(redirectAttr)
				.warning("잘못된 날짜 형식입니다.")
				.build();
		}
		
		return "redirect:/rooms/detail/" + roomId;
	}
	
	
	
	@GetMapping("/detail/{reservation_id}")
	public String detail(Principal principal, Model model, @PathVariable("reservation_id") Integer reservationId) {
		Reservation reservation = reservationService.get(reservationId);
		
		if (reservation == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		if (!(reservation.getGuest().getUsername().equals(principal.getName()) ||
				reservation.getRoom().getHost().getUsername().equals(principal.getName()))) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		model.addAttribute("reservation", reservation);
		return "reservations/detail";
	}
	
	
	
	@GetMapping("/cancel/{reservation_id}")
	public String cancel(Principal principal, Model model, @PathVariable("reservation_id") Integer reservationId) {
		Reservation reservation = reservationService.get(reservationId);
		
		if (reservation == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		if (!(reservation.getGuest().getUsername().equals(principal.getName()) ||
				reservation.getRoom().getHost().getUsername().equals(principal.getName()))) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		try {
			reservationService.cancel(reservationId);
		} catch (DataDoesNotExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		return "redirect:/reservations/detail/" + reservationId;
	}
	
	
	
	
	@GetMapping("/confirm/{reservation_id}")
	public String confirm(Principal principal, Model model, @PathVariable("reservation_id") Integer reservationId) {
		Reservation reservation = reservationService.get(reservationId);
		
		if (reservation == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		if (!reservation.getRoom().getHost().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		try {
			reservationService.confirm(reservationId);
		} catch (DataDoesNotExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		return "redirect:/reservations/detail/" + reservationId;
	}
	
	
	
}
