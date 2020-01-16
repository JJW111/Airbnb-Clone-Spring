package com.clone.airbnb.formatter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.format.Formatter;

import com.clone.airbnb.entity.HouseRule;
import com.clone.airbnb.repository.HouseRuleRepository;
import com.clone.airbnb.utils.BeanUtils;

public class HouseRuleFormatter implements Formatter<List<HouseRule>>{

	@Override
	public String print(List<HouseRule> object, Locale locale) {
		return object.toString();
	}

	@Override
	public List<HouseRule> parse(String text, Locale locale) throws ParseException {
		List<HouseRule> houseRules = null;

		if (!text.trim().isEmpty()) {
			houseRules = new ArrayList<>();
			
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
