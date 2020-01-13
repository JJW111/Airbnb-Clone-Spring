package com.clone.airbnb.formatter;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.springframework.format.Formatter;

import com.clone.airbnb.entity.HouseRule;
import com.clone.airbnb.repository.HouseRuleRepository;
import com.clone.airbnb.utils.BeanUtils;

public class HouseRuleFormatter implements Formatter<Set<HouseRule>>{

	@Override
	public String print(Set<HouseRule> object, Locale locale) {
		return object.toString();
	}

	@Override
	public Set<HouseRule> parse(String text, Locale locale) throws ParseException {
		Set<HouseRule> houseRules = null;

		if (!text.trim().isEmpty()) {
			houseRules = new HashSet<>();
			
			for (String s : text.split(",")) {
				Integer id = Integer.valueOf(s);
				HouseRule houseRule = ((HouseRuleRepository) BeanUtils.getBean(HouseRuleRepository.class)).findById(id).get();
				if (houseRule == null) return null;
				houseRules.add(houseRule);
			}
		}
		
		return houseRules; 
	}
	
}
