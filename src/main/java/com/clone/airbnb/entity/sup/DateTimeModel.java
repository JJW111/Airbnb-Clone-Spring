package com.clone.airbnb.entity.sup;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

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
public abstract class DateTimeModel {
	@Column(name = "created", nullable = false)
	private Date created;

	@Column(name = "updated")
	private Date updated = null;
	
	@PrePersist
	protected void onCreate() {
		created = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updated = new Date();
	}
	
	public DateTimeModel(Date created, Date updated) {
		this.created = created;
		this.updated = updated;
	}
}
