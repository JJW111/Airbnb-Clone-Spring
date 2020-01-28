package com.clone.airbnb.entity.sup;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.clone.airbnb.admin.form.annotation.TextForm;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode(of = "id")
public abstract class AbstractItem {
	@Id
    @GeneratedValue
    private Integer id;
	
	@TextForm(blank = false)
	@Column(name="name", nullable = false)
	@NotBlank
	@Size(max = 150)
	private String name;
}
