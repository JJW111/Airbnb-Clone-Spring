package com.clone.airbnb.entity.sup;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.clone.airbnb.admin.form.annotation.TextForm;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public abstract class AbstractItem {
	@TextForm(blank = false)
	@Column(name="name", nullable = false)
	private String name;
}
