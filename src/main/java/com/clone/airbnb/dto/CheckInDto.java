package com.clone.airbnb.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.clone.airbnb.common.Common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CheckInDto {
	private Integer id;
	@DateTimeFormat(pattern = Common.DATE_FORMAT)
	@NotNull
	private Date checkIn;
	@DateTimeFormat(pattern = Common.DATE_FORMAT)
	@NotNull
	private Date checkOut;
}
