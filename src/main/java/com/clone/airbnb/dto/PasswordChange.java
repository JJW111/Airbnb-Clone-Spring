package com.clone.airbnb.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordChange {
	private String originPassword;
	@NotBlank(message = "")
	@Size(min = 8, max = 30, message = "")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "영문자로 시작하여 8자 이상이어야 합니다.")
	private String password;
	private String retypePassword;
}
