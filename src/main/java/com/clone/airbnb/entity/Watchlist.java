package com.clone.airbnb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.clone.airbnb.admin.entity.AdminFormEntity;
import com.clone.airbnb.admin.form.annotation.EntityForm;
import com.clone.airbnb.admin.form.annotation.JoinManyForm;
import com.clone.airbnb.admin.form.annotation.TextForm;
import com.clone.airbnb.dto.SafeUser;
import com.clone.airbnb.repository.RoomRepository;
import com.clone.airbnb.repository.UserRepository;
import com.clone.airbnb.utils.ValidUtils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EntityForm
@Entity
@Table(name = "watchlist")
@ToString()
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Watchlist implements AdminFormEntity<Watchlist>{
	
	
	@Id
    @GeneratedValue
    private Integer id;
	
	
	
	@TextForm(blank = false, maxlength = 100, placeholder = "리스트 이름, 최대 30자까지")
	@Column(nullable = false)
	@Length(max = 30)
	private String name;
	
	
	
	@JoinManyForm(field = "username", defaultOption = "----- Select Users -----", repository = UserRepository.class, blank = false)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			  name = "watchlist_user",
			  joinColumns = @JoinColumn(name = "watchlist_id"), 
			  inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> users;
	
	
	
	@JoinManyForm(field = "id", defaultOption = "----- Select Rooms -----", repository = RoomRepository.class, blank = false)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			  name = "watchlist_room",
			  joinColumns = @JoinColumn(name = "watchlist_id"), 
			  inverseJoinColumns = @JoinColumn(name = "room_id"))
	private List<Room> rooms;
	
	
	
	
	
	private void setUsers(List<User> users) {
		if (users == null) return;
		if (this.users == null) {
			this.users = new ArrayList<>();
		}
		this.users.clear();
		this.users.addAll(users);
	}
	
	
	
	
	private void setRooms(List<Room> rooms) {
		if (rooms == null) return;
		if (this.rooms == null) {
			this.rooms = new ArrayList<>();
		}
		this.rooms.clear();
		this.rooms.addAll(rooms);
	}

	
	
	
	public int countUsers() {
		if (users == null) return 0;
		return users.size();
	}
	
	
	
	public int countRooms() {
		if (rooms == null) return 0;
		return rooms.size();
	}
	
	
	
	@Getter
    @ToString
	public static class Builder {
	    private Integer id;
	    @NotBlank
	    @Size(max = 30)
		private String name;
	    @NotNull
	    @Size(max = 5)
		private List<SafeUser> users;
	    @NotNull
	    @Size(max = 30)
		private List<Room> rooms;
	    
	    
	    
	    public Builder setId(Integer id) {
	    	this.id = id;
	    	return this;
	    }
	    
	    
	    
	    public Builder setName(String name) {
	    	this.name = name;
	    	return this;
	    }
	    
	    
	    
	    public Builder setUsers(List<SafeUser> users) {
	    	this.users = users;
	    	return this;
	    }
	    
	    
	    
	    public Builder setRooms(List<Room> rooms) {
	    	this.rooms = rooms;
	    	return this;
	    }
	    
	    
	    
	    public Watchlist build() {
	    	return new Watchlist(this);
	    }
	    
	}
	
	
	
	
	private Watchlist(Builder builder) {
		this.setId(builder.getId());
		this.setName(builder.getName());
		this.setUsers(User.toUsers(builder.getUsers()));
		this.setRooms(builder.getRooms());
	}
	
	
	
	
	public static Builder builder() {
		return new Builder();
	}
	
	
	
	public Builder toBuilder() {
		final List<SafeUser> safeUsers = new ArrayList<>();
		
		if (ValidUtils.isValid(this.getUsers())) {
			this.getUsers().forEach(e -> {
				if (e != null) {
					safeUsers.add(e.toSafeUser());
				}
			});
		}
		
		return builder()
				.setId(this.getId())
				.setName(this.getName())
				.setRooms(this.getRooms())
				.setUsers(safeUsers);
	}
	
	
	
	
	
	@Override
	public Watchlist deepClone() {
		return this.toBuilder().build();
	}



	@Override
	public void override(Watchlist t) {
		if (t.getId()			!= null) this.setId(t.getId());
		if (t.getName()			!= null) this.setName(t.getName());
		if (t.getUsers()		!= null) this.setUsers(t.getUsers());
		if (t.getRooms()		!= null) this.setRooms(t.getRooms());
	}
	
}
