package com.clone.airbnb.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clone.airbnb.dto.RoomUpdateDto;
import com.clone.airbnb.entity.projection.RoomDetail;
import com.clone.airbnb.entity.projection.RoomPhotos;
import com.clone.airbnb.entity.projection.RoomUpdate;
import com.clone.airbnb.entity.values.SelectValues;
import com.clone.airbnb.exception.DataDoesNotExistsException;
import com.clone.airbnb.exception.ListSizeOutOfBoundsException;
import com.clone.airbnb.messages.RedirectMessageSystem;
import com.clone.airbnb.service.RoomService;

@Controller
@RequestMapping(path="/rooms")
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	@Autowired
	private SelectValues selectValues;
	
	
	@GetMapping(path="/detail")
	public String detail(Model model, @RequestParam("id") Integer id) {
		RoomDetail roomDetail = roomService.getRoomDetail(id);
		
		if (roomDetail == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		model.addAttribute("room", roomDetail);
		return "rooms/room_detail";
	}
	
	
	
	@GetMapping(path="/search")
	public String search(Model model, @RequestParam("city") String city) {
		return "rooms/search";
	}
	
	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping(path="/edit")
	public String roomEdit(Principal principal, Model model, @RequestParam("id") int id) {
		RoomUpdate roomUpdate = roomService.getRoomUpdate(id, principal.getName());
		
		if (roomUpdate == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		model.addAttribute("room", roomUpdate);
		model.addAttribute("selectValues", selectValues);
		return "rooms/room_edit";
	}
	
	
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping(path="/edit")
	public String editRoom(Principal principal, Model model, @Valid @ModelAttribute("room") RoomUpdateDto dto, RedirectAttributes redirectAttr) {
		try {
			roomService.update(dto, principal.getName());
		} catch (DataDoesNotExistsException e) {
			RedirectMessageSystem.builder(redirectAttr)
				.error("해당 방이 존재하지 않습니다.")
				.build();
			return "redirect:/";
		}
		
		RedirectMessageSystem.builder(redirectAttr)
			.success("방 업데이트 성공!")
			.build();
		
		return "redirect:/rooms/detail?id=" + dto.getId();
	}
	
	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping(path="/photos")
	public String photos(Principal principal, Model model, @RequestParam("room_id") Integer roomId) {
		RoomPhotos roomPhotos = roomService.getRoomPhotos(roomId, principal.getName());
		
		if (roomPhotos == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		model.addAttribute("room", roomPhotos);
		return "rooms/room_photos";
	}
	
	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping(path="/photo/delete")
	public String deletePhoto(Principal principal, @RequestParam("room_id") Integer roomId, @RequestParam("photo_id") Integer photoId, RedirectAttributes redirectAttr) {
		try {
			roomService.deletePhoto(roomId, photoId, principal.getName());
			return "redirect:/rooms/photos?room_id=" + roomId;
		} catch(ListSizeOutOfBoundsException e) {
			RedirectMessageSystem.builder(redirectAttr)
				.error("최소 5장의 사진이 필요합니다.")
				.build();
			return "redirect:/rooms/photos?room_id=" + roomId;
		} catch (DataDoesNotExistsException e) {
			return "redirect:/wrong_access";
		}
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping(path="/photo/add")
	public String photoAdd() {
		return "rooms/photo_create";
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping(path="/photo/add")
	public String addPhoto(Principal principal, @RequestParam("room_id") Integer roomId, 
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
				return "redirect:/rooms/photos?room_id=" + roomId;
			} catch (DataDoesNotExistsException e) {
				return "redirect:/wrong_access";
			}
		}
	}
	
}
