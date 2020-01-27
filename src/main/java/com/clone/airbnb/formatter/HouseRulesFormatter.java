package com.clone.airbnb.formatter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.clone.airbnb.entity.HouseRule;
import com.clone.airbnb.repository.HouseRuleRepository;

@Component
public class HouseRulesFormatter implements Formatter<List<HouseRule>>{

	@Autowired
	private HouseRuleRepository houseRuleRepository;
	
	@Override
	public String print(List<HouseRule> object, Locale locale) {
		return object.toString();
	}

	@Override
	public List<HouseRule> parse(String text, Locale locale) throws ParseException {
		if (text == null) return null;
		
		List<HouseRule> houseRules = null;

		if (!text.trim().isEmpty()) {
			houseRules = new ArrayList<>();
			
			for (String s : text.split(",")) {
				Integer id = Integer.valueOf(s);
				
				Optional<HouseRule> opt = houseRuleRepository.findById(id);
				
				if (opt.isPresent()) {
					houseRules.add(opt.get());
				}
			}
		}
		
		if (houseRules != null && !houseRules.isEmpty()) {
			return houseRules; 
		} else {
			return null;
		}
	}
	
}
