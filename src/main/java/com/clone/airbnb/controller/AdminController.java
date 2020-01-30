package com.clone.airbnb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.clone.airbnb.admin.AdminWebPage;
import com.clone.airbnb.admin.schema.AdminEntityConfiguration;
import com.clone.airbnb.admin.schema.vo.AdminEntity;
import com.clone.airbnb.admin.schema.vo.Entities;
import com.clone.airbnb.common.SquarePageBlock;
import com.clone.airbnb.dto.admin.DtoFactory;
import com.clone.airbnb.dto.admin.Edit;
import com.clone.airbnb.entity.values.SelectValues;
import com.clone.airbnb.utils.ReflectionInvocator;

@Controller
@RequestMapping(path="/admin")
public class AdminController {
	
	@Autowired
	private AdminWebPage adminWebPage;

	@Autowired
	private SelectValues selectValues;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/** 리스트 화면에서 출력할 블럭당 페이지 수 */
	private static final int PAGE_BLOCK = 10;
	
	
	
	
	
	@GetMapping(path="")
	public String home(Model model) {
		model.addAttribute("groups", adminWebPage.getAdminEntityProvider().getAdminDefinitionObject().getGroups());
		return "admin/index";
	}
	
	
	
	
	@GetMapping(path="/group")
	public String group(Model model, @RequestParam("g") String groupName) {
		model.addAttribute("group", adminWebPage.getAdminEntityProvider().getAdminDefinitionObject().getGroups().get(groupName));
		return "admin/group";
	}
	
	
	
	
	
	/**
	 * {@code entityName} 에 해당하는 entity 의 DB 리스트를 불러와 출력한다.
	 * 
	 * @param entityName
	 * @param pageable
	 * @return
	 */
	@GetMapping(path="/entity")
	public String entity(Model model, @RequestParam("e") String entityName, 
			@PageableDefault(sort = "id", direction = Sort.Direction.DESC, value = 10) Pageable pageable) {
		Page<Object> page;
		
		page = adminWebPage.findAll(entityName, pageable);
		
		if (page.getTotalPages() > 0 && page.getNumber() + 1 > page.getTotalPages()) {
			return "redirect:/admin/entity?e=" + entityName + "&page=" + (page.getTotalPages() - 1);
		}
		
		AdminEntity adminEntity =  adminWebPage.getAdminEntityProvider().getAdminDefinitionObject().getAdminEntity();
		Entities entities = adminWebPage.getEntityProvider().getEntities();
		
		model.addAttribute("groupName", adminEntity.get(entityName).group());
		model.addAttribute("entity", entities.get(entityName));
		model.addAttribute("fieldList", adminEntity.get(entityName).fieldList());
		model.addAttribute("page", page);
		model.addAttribute("pageBlock", new SquarePageBlock(page, PAGE_BLOCK));
		model.addAttribute("selectValues", selectValues);
		model.addAttribute("reflectionInvocator", ReflectionInvocator.getInstance());
		return "admin/entity";
	}
	
	
	
	
	
	@GetMapping(path="/entity/add")
	public String addEntity(Model model, @RequestParam("e") String entityName) {
		AdminEntity adminEntity =  adminWebPage.getAdminEntityProvider().getAdminDefinitionObject().getAdminEntity();
		Entities entities = adminWebPage.getEntityProvider().getEntities();
		model.addAttribute("groupName", adminEntity.get(entityName).group());
		model.addAttribute("entity", entities.get(entityName));
		model.addAttribute("fieldSet", adminEntity.get(entityName).fieldSet());
		model.addAttribute("entityObj", DtoFactory.get(entityName, Edit.ADD));
		model.addAttribute("reflectionInvocator", ReflectionInvocator.getInstance());
		model.addAttribute("selectValues", selectValues);
		
		return "admin/add";
	}
	
	
	
	
	
	@PostMapping(path="/entity/add")
	public String checkEntityAndAddProcess(Model model, WebRequest webRequest, @RequestParam("e") String entityName, Pageable pageable) {
		AdminEntityConfiguration config =  adminWebPage.getAdminEntityProvider().getAdminDefinitionObject().getAdminEntity().get(entityName);
		Entities entities = adminWebPage.getEntityProvider().getEntities();
		
		Object dtoObj = DtoFactory.get(entityName, Edit.ADD);
		
		BindingResult result = adminWebPage.bind(entityName, dtoObj, webRequest);
		
		Object entityObj = null;
		
		if ("User".equals(entityName)) {
			entityObj = ReflectionInvocator.invoke(dtoObj, "toOriginal", PasswordEncoder.class, passwordEncoder);
		} else {
			entityObj = ReflectionInvocator.invoke(dtoObj, "toOriginal");
		}
		
		if (!result.hasErrors()) {
			adminWebPage.save(entityName, entityObj);
			return "redirect:/admin/entity?e=" + entityName;
		} else {
			model.addAttribute("groupName", config.group());
			model.addAttribute("entity", entities.get(entityName));
			model.addAttribute("fieldSet", config.fieldSet());
			model.addAttribute(BindingResult.class.getName() + ".entityObj", result);
			model.addAttribute("entityObj", result.getTarget());
			model.addAttribute("selectValues", selectValues);
			model.addAttribute("reflectionInvocator", ReflectionInvocator.getInstance());
			return "admin/add";
		}
		
	}
	
	
	
	
	
	@GetMapping(path="/entity/update")
	public String update(Model model, @RequestParam("e") String entityName, @RequestParam("id") Integer id) {
		AdminEntityConfiguration config =  adminWebPage.getAdminEntityProvider().getAdminDefinitionObject().getAdminEntity().get(entityName);
		Entities entities = adminWebPage.getEntityProvider().getEntities();
		
		Object entityObj = adminWebPage.findById(entityName, id);
		
		model.addAttribute("id", id);
		model.addAttribute("groupName", config.group());
		model.addAttribute("entity", entities.get(entityName));
		model.addAttribute("fieldSet", config.fieldSet());
		model.addAttribute("entityObj", entityObj);
		model.addAttribute("selectValues", selectValues);
		model.addAttribute("reflectionInvocator", ReflectionInvocator.getInstance());
		return "admin/update";
	}
	
	
	
	
	
	@PostMapping(path="/entity/update")
	public String updateProcess(Model model, WebRequest webRequest, @RequestParam("e") String entityName, @RequestParam("id") Integer id, Pageable pageable) {
		AdminEntityConfiguration config =  adminWebPage.getAdminEntityProvider().getAdminDefinitionObject().getAdminEntity().get(entityName);
		Entities entities = adminWebPage.getEntityProvider().getEntities();
		
		Object dtoObj = DtoFactory.get(entityName, Edit.UPDATE);
		
		BindingResult result = adminWebPage.bind(entityName, dtoObj, webRequest);
		
		Object entityObj = ReflectionInvocator.invoke(dtoObj, "toOriginal");
		
		if (!result.hasErrors()) {
			adminWebPage.update(entityName, entityObj, id);
			return "redirect:/admin/entity?e=" + entityName + "&page=" + pageable.getPageNumber();
		} else {
			model.addAttribute("id", id);
			model.addAttribute("groupName", config.group());
			model.addAttribute("entity", entities.get(entityName));
			model.addAttribute("fieldSet", config.fieldSet());
			model.addAttribute(BindingResult.class.getName() + ".entityObj", result);
			model.addAttribute("entityObj", result.getTarget());
			model.addAttribute("selectValues", selectValues);
			model.addAttribute("reflectionInvocator", ReflectionInvocator.getInstance());
			return "admin/update";
		}
		
	}
	
	
	
	
	
	@GetMapping(path="/entity/delete")
	public String delete(Model model, @RequestParam("e") String entityName, @RequestParam("id") Integer id, Pageable pageable) {
		AdminEntityConfiguration config =  adminWebPage.getAdminEntityProvider().getAdminDefinitionObject().getAdminEntity().get(entityName);
		Entities entities = adminWebPage.getEntityProvider().getEntities();
		
		model.addAttribute("page", pageable.getOffset());
		model.addAttribute("id", id);
		model.addAttribute("groupName", config.group());
		model.addAttribute("entityName", entities.get(entityName).getName());
		return "admin/delete";
	}
	
	
	
	
	
	@PostMapping(path="/entity/delete")
	public String deleteProcess(Model model, @RequestParam("e") String entityName, @RequestParam("id") Integer id, Pageable pageable) {
		adminWebPage.delete(entityName, id);
		return "redirect:/admin/entity?e=" + entityName + "&page=" + pageable.getOffset();
	}
	
}
