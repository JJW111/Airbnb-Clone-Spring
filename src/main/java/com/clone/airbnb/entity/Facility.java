package com.clone.airbnb.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.clone.airbnb.admin.entity.AdminFormEntity;
import com.clone.airbnb.admin.form.annotation.EntityForm;
import com.clone.airbnb.entity.sup.AbstractItem;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EntityForm
@Entity
@Table(name = "facility")
@ToString(exclude = "rooms", callSuper = true)
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Facility extends AbstractItem implements AdminFormEntity<Facility> {
	
	@Id
    @GeneratedValue
    private Integer id;
	
	@ManyToMany(mappedBy = "facilities", cascade = CascadeType.REMOVE)
	private Set<Room> rooms;
	
	
	
	/******** Builder 클래스 선언 **********/
    @Getter
    @ToString
    public static class Builder {
    	private Integer id;
    	@NotBlank
    	@Size(max = 150)
    	private String name;
    	
    	public Builder setId(Integer id) {
    		this.id = id;
    		return this;
    	}
    	
    	public Builder setName(String name) {
    		this.name = name;
    		return this;
    	}
    	
    	public Facility build() {
    		return new Facility(this);
    	}
    	
    }
    
    
    
    private Facility(Builder builder) {
    	this.id = builder.id;
    	this.setName(builder.name);
    }
    
    
    
    public static Builder builder() {
    	return new Builder();
    }
	
	
	
	@Override
	public Facility deepClone() {
		return builder()
				.setId(this.getId())
				.setName(this.getName())
				.build();
	}

	
	
	@Override
	public void override(Facility t) {
		if (t.getId() 		!= null) this.setId(t.getId());
		if (t.getName() 	!= null) this.setName(t.getName());
	}
	
}
