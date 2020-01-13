package com.clone.airbnb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.clone.airbnb.admin.AdminWebPage;
import com.clone.airbnb.admin.schema.AdminEntityConfiguration;
import com.clone.airbnb.admin.schema.vo.AdminEntity;
import com.clone.airbnb.admin.schema.vo.Entities;
import com.clone.airbnb.common.SquarePageBlock;
import com.clone.airbnb.dto.SafeUser;
import com.clone.airbnb.entity.User;
import com.clone.airbnb.utils.ReflectionInvocator;

@Controller
@RequestMapping(path="/admin")
public class AdminController {
	
	@Autowired
	private AdminWebPage adminWebPage;
	
	/** 리스트 화면에서 출력할 블럭당 페이지 수 */
	private static final int PAGE_BLOCK = 10;
	
	
	
	
	
	@GetMapping(path="")
	public String home(Model model) {
		model.addAttribute("groups", adminWebPage.getAdminEntityProvider().getAdminDefinitionObject().getGroups());
		return "admin/index";
	}
	
	
	
	
	@GetMapping(path="/group")
	public ModelAndView group(@RequestParam("g") String groupName) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("group", adminWebPage.getAdminEntityProvider().getAdminDefinitionObject().getGroups().get(groupName));
		mv.setViewName("admin/group");
		
		return mv;
	}
	
	
	
	
	
	/**
	 * {@code entityName} 에 해당하는 entity 의 DB 리스트를 불러와 출력한다.
	 * 
	 * @param entityName
	 * @param pageable
	 * @return
	 */
	@GetMapping(path="/entity")
	public ModelAndView entity(@RequestParam("e") String entityName, Pageable pageable, @RequestParam(name="orderBy", defaultValue = "id") String orderBy, @RequestParam(name="isAsc", defaultValue = "false") boolean isAsc) {
		ModelAndView mv = new ModelAndView();
		
		Page<Object> page;
		
		if ("User".equals(entityName)) {
			page = adminWebPage.findByOrderBy(entityName, orderBy, (isAsc ? "Asc" : "Desc"), pageable, SafeUser.class);
			System.out.println(User.toUser((SafeUser) page.getContent().get(0)));
		} else {
			page = adminWebPage.findByOrderBy(entityName, orderBy, (isAsc ? "Asc" : "Desc"), pageable);
		}
		
		
		if (page.getTotalPages() > 0 && page.getNumber() + 1 > page.getTotalPages()) {
			mv.setViewName("redirect:/admin/entity?e=" + entityName + "&page=" + (page.getTotalPages() - 1) + "&size=" + page.getSize());
			return mv;
		}
		
		AdminEntity adminEntity =  adminWebPage.getAdminEntityProvider().getAdminDefinitionObject().getAdminEntity();
		Entities entities = adminWebPage.getEntityProvider().getEntities();
		
		mv.addObject("groupName", adminEntity.get(entityName).group());
		mv.addObject("entity", entities.get(entityName));
		mv.addObject("fieldList", adminEntity.get(entityName).fieldList());
		mv.addObject("page", page);
		mv.addObject("pageBlock", new SquarePageBlock(page, PAGE_BLOCK));
		mv.addObject("reflectionInvocator", ReflectionInvocator.getInstance());
		mv.setViewName("admin/entity");
		return mv;
	}
	
	
	
	
	
	@GetMapping(path="/entity/add")
	public ModelAndView addEntity(@RequestParam("e") String entityName) {
		ModelAndView mv = new ModelAndView();
		
		AdminEntity adminEntity =  adminWebPage.getAdminEntityProvider().getAdminDefinitionObject().getAdminEntity();
		Entities entities = adminWebPage.getEntityProvider().getEntities();
		
		mv.addObject("groupName", adminEntity.get(entityName).group());
		mv.addObject("entity", entities.get(entityName));
		mv.addObject("fieldSet", adminEntity.get(entityName).fieldSet());
		mv.addObject("entityObj", ReflectionInvocator.invoke(entities.get(entityName).getEntityClass(), "builder"));
		
		mv.setViewName("admin/add");
		
		return mv;
	}
	
	
	
	
	
	@PostMapping(path="/entity/add")
	public String checkEntityAndAddProcess(Model model, WebRequest webRequest, @RequestParam("e") String entityName, Pageable pageable) {
		AdminEntityConfiguration config =  adminWebPage.getAdminEntityProvider().getAdminDefinitionObject().getAdminEntity().get(entityName);
		Entities entities = adminWebPage.getEntityProvider().getEntities();
		
		Object builderObj = ReflectionInvocator.invoke(entities.get(entityName).getEntityClass(), "builder");
		
		BindingResult result = adminWebPage.bind(entityName, builderObj, webRequest);
		
		boolean hasErrors = result.hasErrors();
		Object entityObj = null;
		BindingResult secondResult = null;
		
		if (!hasErrors) {
			entityObj = ReflectionInvocator.invoke(result.getTarget(), "build");
			secondResult = adminWebPage.validate(entityName, entityObj, webRequest);
			hasErrors = secondResult.hasErrors();
		}
		
		if (!hasErrors) {
			adminWebPage.save(entityName, entityObj);
			return "redirect:/admin/entity?e=" + entityName + "&size=" + pageable.getPageSize();
		} else {
			BindingResult r = secondResult == null ? result : secondResult;
			model.addAttribute("groupName", config.group());
			model.addAttribute("entity", entities.get(entityName));
			model.addAttribute("fieldSet", config.fieldSet());
			model.addAttribute("reflectionInvocator", ReflectionInvocator.getInstance());
			model.addAttribute(BindingResult.class.getName() + ".entityObj", r);
			model.addAttribute("entityObj", r.getTarget());
			return "admin/add";
		}
		
	}
	
	
	
	
	
	@GetMapping(path="/entity/update")
	public String update(Model model, @RequestParam("e") String entityName, @RequestParam("id") Integer id) {
		AdminEntityConfiguration config =  adminWebPage.getAdminEntityProvider().getAdminDefinitionObject().getAdminEntity().get(entityName);
		Entities entities = adminWebPage.getEntityProvider().getEntities();
		
		Object entityObj;
		
		if ("User".equals(entityName)) {
			entityObj = adminWebPage.findById(entityName, id, SafeUser.class);
		} else {
			entityObj = adminWebPage.findById(entityName, id);
		}
		
		model.addAttribute("id", id);
		model.addAttribute("groupName", config.group());
		model.addAttribute("entity", entities.get(entityName));
		model.addAttribute("fieldSet", config.fieldSet());
		model.addAttribute("entityObj", entityObj);
		model.addAttribute("reflectionInvocator", ReflectionInvocator.getInstance());
		return "admin/update";
	}
	
	
	
	
	
	@PostMapping(path="/entity/update")
	public String updateProcess(Model model, WebRequest webRequest, @RequestParam("e") String entityName, @RequestParam("id") Integer id, Pageable pageable) {
		AdminEntityConfiguration config =  adminWebPage.getAdminEntityProvider().getAdminDefinitionObject().getAdminEntity().get(entityName);
		Entities entities = adminWebPage.getEntityProvider().getEntities();
		
		Object builderObj = ReflectionInvocator.invoke(entities.get(entityName).getEntityClass(), "builder");
		
		BindingResult result = adminWebPage.bind(entityName, builderObj, webRequest);
		
		boolean hasErrors = result.hasErrors();
		Object entityObj = null;
		BindingResult secondResult = null;

		if (!hasErrors) {
			entityObj = ReflectionInvocator.invoke(result.getTarget(), "build");
			secondResult = adminWebPage.validate(entityName, entityObj, webRequest);
			hasErrors = secondResult.hasErrors();
		}
		
		if (!hasErrors) {
			adminWebPage.update(entityName, entityObj, id);
			return "redirect:/admin/entity?e=" + entityName + "&page=" + pageable.getOffset() + "&size=" + pageable.getPageSize();
		} else {
			BindingResult r = secondResult == null ? result : secondResult;
			model.addAttribute("id", id);
			model.addAttribute("groupName", config.group());
			model.addAttribute("entity", entities.get(entityName));
			model.addAttribute("fieldSet", config.fieldSet());
			model.addAttribute("reflectionInvocator", ReflectionInvocator.getInstance());
			model.addAttribute(BindingResult.class.getName() + ".entityObj", r);
			model.addAttribute("entityObj", r.getTarget());
			return "admin/update";
		}
		
	}
	
	
	
	
	
	@GetMapping(path="/entity/delete")
	public String delete(Model model, @RequestParam("e") String entityName, @RequestParam("id") Integer id, Pageable pageable) {
		AdminEntityConfiguration config =  adminWebPage.getAdminEntityProvider().getAdminDefinitionObject().getAdminEntity().get(entityName);
		Entities entities = adminWebPage.getEntityProvider().getEntities();
		
		model.addAttribute("page", pageable.getOffset());
		model.addAttribute("size", pageable.getPageSize());
		model.addAttribute("id", id);
		model.addAttribute("groupName", config.group());
		model.addAttribute("entityName", entities.get(entityName).getName());
		return "admin/delete";
	}
	
	
	
	
	
	@PostMapping(path="/entity/delete")
	public String deleteProcess(Model model, @RequestParam("e") String entityName, @RequestParam("id") Integer id, Pageable pageable) {
		adminWebPage.delete(entityName, id);
		return "redirect:/admin/entity?e=" + entityName + "&page=" + pageable.getOffset() + "&size=" + pageable.getPageSize();
	}
	
}
