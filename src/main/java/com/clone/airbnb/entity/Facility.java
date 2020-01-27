package com.clone.airbnb.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.clone.airbnb.admin.entity.AdminFormEntity;
import com.clone.airbnb.admin.form.annotation.EntityForm;
import com.clone.airbnb.entity.sup.AbstractItem;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EntityForm
@Entity
@Table(name = "facility")
@ToString(callSuper = true)
@Getter
@Setter
public class Facility extends AbstractItem implements AdminFormEntity<Facility> {
	@Override
	public void override(Facility t) {
		if (t.getId() 		!= null) this.setId(t.getId());
		if (t.getName() 	!= null) this.setName(t.getName());
	}
}
