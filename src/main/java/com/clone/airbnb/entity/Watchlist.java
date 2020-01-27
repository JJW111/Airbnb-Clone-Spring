package com.clone.airbnb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.clone.airbnb.admin.entity.AdminFormEntity;
import com.clone.airbnb.admin.form.annotation.EntityForm;
import com.clone.airbnb.admin.form.annotation.JoinManyForm;
import com.clone.airbnb.admin.form.annotation.TextForm;

import lombok.Getter;
import lombok.Setter;

@EntityForm
@Entity
@Table(name = "watchlist")
@Getter
@Setter
public class Watchlist implements AdminFormEntity<Watchlist> {
	
	
	@Id
    @GeneratedValue
    private Integer id;
	
	
	
	@TextForm(blank = false, maxlength = 100, placeholder = "리스트 이름, 최대 30자까지")
	@Column(nullable = false)
    @Size(min = 1, max = 30, message="최대 30자까지 가능합니다.")
	private String name;
	
	
	
	@JoinManyForm(itemLabel = "username", itemValue="id", method = "users", defaultOption = "----- Select Users -----")
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(
			  name = "watchlist_user",
			  joinColumns = @JoinColumn(name = "watchlist_id"), 
			  inverseJoinColumns = @JoinColumn(name = "user_id"))
    @Size(max = 5)
	private List<User> users;
	
	
	
	@JoinManyForm(itemLabel = "id", itemValue="id", method = "rooms", defaultOption = "----- Select Rooms -----")
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(
			  name = "watchlist_room",
			  joinColumns = @JoinColumn(name = "watchlist_id"), 
			  inverseJoinColumns = @JoinColumn(name = "room_id"))
    @Size(max = 30)
	private List<Room> rooms;
	
	
	
	
	
	public void setUsers(List<User> users) {
		if (users == null) return;
		if (this.users == null) {
			this.users = new ArrayList<>();
		}
		this.users.clear();
		this.users.addAll(users);
	}
	
	
	
	
	public void setRooms(List<Room> rooms) {
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
	
	
	
	@Override
	public void override(Watchlist t) {
		if (t.getId()			!= null) this.setId(t.getId());
		if (t.getName()			!= null) this.setName(t.getName());
		if (t.getUsers()		!= null) this.setUsers(t.getUsers());
		if (t.getRooms()		!= null) this.setRooms(t.getRooms());
	}
	
	
	
	@Override
	public String toString() {
		return "Watchlist[id=" + id + ",name=" + name 
				+ ",users=" + (users != null ? users.size() + "users" : null)
				+ ",rooms=" + (rooms != null ? rooms.size() + "rooms" : null) + "]";
	}
	
}
