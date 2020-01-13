package com.clone.airbnb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.clone.airbnb.common.SquarePageBlock;
import com.clone.airbnb.entity.Room;
import com.clone.airbnb.service.RoomService;

@Controller
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	private int PAGE_BLOCK = 10;
	
	@GetMapping(path="")
	public String home(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, value = 10) Pageable pageable ) {
		Page<Room> page = roomService.rooms(pageable);
		model.addAttribute("page", page);
		model.addAttribute("rooms", page.getContent());
		model.addAttribute("pageBlock", new SquarePageBlock(page, PAGE_BLOCK));
		return "index";
	}
	
	
	
	@GetMapping(path="/search")
	public String search(Model model, @RequestParam("city") String city) {
		return "room/search";
	}
	
}