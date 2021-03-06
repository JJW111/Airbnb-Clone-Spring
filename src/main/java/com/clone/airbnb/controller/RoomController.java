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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clone.airbnb.calendar.RoomDetailCalendar;
import com.clone.airbnb.dto.RoomAddDto;
import com.clone.airbnb.dto.RoomUpdateDto;
import com.clone.airbnb.entity.Room;
import com.clone.airbnb.entity.values.SelectValues;
import com.clone.airbnb.exception.DataDoesNotExistsException;
import com.clone.airbnb.exception.ListSizeOutOfBoundsException;
import com.clone.airbnb.exception.UserDoesNotExistsException;
import com.clone.airbnb.messages.RedirectMessageSystem;
import com.clone.airbnb.service.RoomService;

@Controller
@RequestMapping(path="/rooms")
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private SelectValues selectValues;
	
	
	
	@GetMapping(path="/detail/{room_id}")
	public String detail(Model model, @PathVariable("room_id") Integer roomId) {
		Room room = roomService.getRoomDetail(roomId);
		
		if (room == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		model.addAttribute("room", room);
		model.addAttribute("calendars", new RoomDetailCalendar().getCalendars());
		return "rooms/room_detail";
	}
	
	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping(path="/add")
	public String roomAdd(Model model) {
		model.addAttribute("room", new RoomAddDto());
		model.addAttribute("selectValues", selectValues);
		return "rooms/room_add";
	}
	
	

	@PreAuthorize("isAuthenticated()")
	@PostMapping(path="/add")
	public String addRoom(Principal principal, Model model, 
			@Valid @ModelAttribute("room") RoomAddDto dto, BindingResult result, 
			RedirectAttributes redirectAttr) {
		if (result.hasErrors()) {
			model.addAttribute("selectValues", selectValues);
			return "rooms/room_add";
		}
		
		try {
			Room newRoom = roomService.addRoom(dto, principal.getName());
			
			RedirectMessageSystem.builder(redirectAttr)
				.success("방 생성 성공!")
				.build();
		
			return "redirect:/rooms/detail/" + newRoom.getId();
		} catch (UserDoesNotExistsException e) {
			return "redirect:/wrong_access";
		}
		
	}
	
	
	
	@GetMapping(path="/search")
	public String search(Model model, @RequestParam("city") String city) {
		return "rooms/search";
	}
	
	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping(path="/{room_id}/edit")
	public String roomEdit(Principal principal, Model model, @PathVariable("room_id") int roomId) {
		Room roomUpdate = roomService.getRoomUpdate(roomId, principal.getName());
		
		if (roomUpdate == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		model.addAttribute("room", roomUpdate);
		model.addAttribute("selectValues", selectValues);
		return "rooms/room_edit";
	}
	
	
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping(path="/{room_id}/edit")
	public String editRoom(Principal principal, Model model,
			@Valid @ModelAttribute("room") RoomUpdateDto dto, BindingResult result,
			RedirectAttributes redirectAttr) {
		if (result.hasErrors()) {
			model.addAttribute("selectValues", selectValues);
			return "rooms/room_edit";
		}
		
		try {
			roomService.updateRoom(dto, principal.getName());
		} catch (DataDoesNotExistsException e) {
			RedirectMessageSystem.builder(redirectAttr)
				.error("해당 방이 존재하지 않습니다.")
				.build();
			return "redirect:/";
		}
		
		RedirectMessageSystem.builder(redirectAttr)
			.success("방 업데이트 성공!")
			.build();
		
		return "redirect:/rooms/detail/" + dto.getId();
	}
	
	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping(path="/photos/{room_id}")
	public String photos(Principal principal, Model model, @PathVariable("room_id") Integer roomId) {
		Room roomPhotos = roomService.getRoomPhotos(roomId, principal.getName());
		
		if (roomPhotos == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		model.addAttribute("room", roomPhotos);
		return "rooms/room_photos";
	}
	
	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping(path="/photo/{room_id}/{photo_id}/delete")
	public String deletePhoto(Principal principal, @PathVariable("room_id") Integer roomId, @PathVariable("photo_id") Integer photoId, RedirectAttributes redirectAttr) {
		try {
			roomService.deletePhoto(roomId, photoId, principal.getName());
			return "redirect:/rooms/photos/" + roomId;
		} catch(ListSizeOutOfBoundsException e) {
			RedirectMessageSystem.builder(redirectAttr)
				.error("최소 5장의 사진이 필요합니다.")
				.build();
			return "redirect:/rooms/photos/" + roomId;
		} catch (DataDoesNotExistsException e) {
			return "redirect:/wrong_access";
		}
	}
	
	
	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping(path="/photo/{room_id}/add")
	public String photoAdd(Model model) {
		return "rooms/photo_create";
	}
	
	
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping(path="/photo/{room_id}/add")
	public String addPhoto(Principal principal, @PathVariable("room_id") Integer roomId, 
			@RequestParam("photoFile") MultipartFile photoFile, RedirectAttributes redirectAttr) {
		if (photoFile.isEmpty()) {
			RedirectMessageSystem.builder(redirectAttr)
				.error("업로드할 파일을 선택하여 주십시오.")
				.build();
			return "redirect:/rooms/photo/add?room_id=" + roomId;
		} else {
			try {
				roomService.addPhoto(photoFile, roomId, principal.getName());
				return "redirect:/rooms/photos?room_id=" + roomId;
			} catch (ListSizeOutOfBoundsException e) {
				RedirectMessageSystem.builder(redirectAttr)
					.error("방 사진은 최대 10개까지 가능합니다.")
					.build();
				return "redirect:/rooms/photos/" + roomId;
			} catch (DataDoesNotExistsException e) {
				return "redirect:/wrong_access";
			}
		}
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping(path="/{room_id}/delete")
	public String deleteRoom(Principal principal, @PathVariable("room_id") Integer roomId, RedirectAttributes redirectAttr) {
		try {
			roomService.deleteRoom(roomId, principal.getName());
			return "redirect:/users/profile";
		} catch (DataDoesNotExistsException e) {
			return "redirect:/wrong_access";
		}
	}
	
}
