package com.clone.airbnb.dto;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import com.clone.airbnb.common.Common;
import com.clone.airbnb.entity.enu.Currency;
import com.clone.airbnb.entity.enu.Gender;
import com.clone.airbnb.entity.enu.Language;
import com.clone.airbnb.entity.enu.LoginMethod;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateProfileDto {
	@NotNull
	private Integer id;
    @Size(min = 1, max = 30)
    private String firstName;
    @Size(min = 1, max = 30)
    private String lastName;
    @Size(max = 100)
    private String bio;
    @DateTimeFormat(pattern = Common.DATE_FORMAT)
    private Date birthdate;
    @NotNull
    private Gender gender;
    @NotNull
    private Language language;
    @NotNull
    private Currency currency;
    private LoginMethod loginMethod;
}
