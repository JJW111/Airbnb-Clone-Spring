package com.clone.airbnb.dto.admin;

import java.util.List;

import javax.validation.constraints.Size;

import com.clone.airbnb.entity.Room;
import com.clone.airbnb.entity.User;
import com.clone.airbnb.entity.Watchlist;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WatchlistDto implements DtoToOriginalSwitcher<Watchlist> {
	@Size(min = 1, max = 30, message="최대 30자까지 가능합니다.")
	private String name;
    @Size(max = 5, message="최대 5명까지의 유저를 선택하여 주십시오.")
	private List<User> users;
    @Size(max = 30, message="최대 30개까지의 방을 선택하여 주십시오.")
	private List<Room> rooms;
	
	
	
	@Override
    public Watchlist toOriginal() {
		Watchlist watchlist = new Watchlist();
		watchlist.setName(this.getName());
		watchlist.setUsers(this.getUsers());
		watchlist.setRooms(this.getRooms());
		
		return watchlist;
    }
}
