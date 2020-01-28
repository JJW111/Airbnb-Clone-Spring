package com.clone.airbnb.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.clone.airbnb.admin.entity.AdminFormEntity;
import com.clone.airbnb.admin.form.annotation.EntityForm;
import com.clone.airbnb.entity.sup.AbstractItem;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EntityForm
@Entity
@Table(name = "amenity")
@ToString(callSuper = true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Amenity extends AbstractItem  implements AdminFormEntity<Amenity> {
	@Override
	public void override(Amenity t) {
		if (t.getId() 		!= null) this.setId(t.getId());
		if (t.getName() 	!= null) this.setName(t.getName());
	}
}
