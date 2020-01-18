package com.clone.airbnb.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.clone.airbnb.entity.Room;
import com.clone.airbnb.service.RoomService;

@Controller
@RequestMapping(path="/rooms")
public class RoomController {

	@Autowired
	private RoomService roomService;
	

	
	@GetMapping(path="/detail")
	public String detail(Model model, @RequestParam("id") Integer id) {
		Room room = roomService.get(id);
		
		if (room == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		model.addAttribute("room", room);
		return "rooms/room_detail";
	}
	
	
	
	@GetMapping(path="/search")
	public String search(Model model, @RequestParam("city") String city) {
		return "rooms/search";
	}
	
	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping(path="/edit")
	public String roomEdit(Model model, @RequestParam("id") int id) {
		Room room = roomService.get(id);
		
		if (room == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		model.addAttribute("room", room);
		return "rooms/room_edit";
	}
	
	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping(path="/photos")
	public String photos(Principal principal, Model model, @RequestParam("id") int id) {
		Room room = roomService.get(id);
		
		if (room == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		model.addAttribute("room", room);
		return "rooms/room_photos";
	}
	
}