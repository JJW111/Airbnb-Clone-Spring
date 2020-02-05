package com.clone.airbnb.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clone.airbnb.calendar.CalendarObject;
import com.clone.airbnb.entity.Reservation;
import com.clone.airbnb.entity.Room;
import com.clone.airbnb.entity.User;
import com.clone.airbnb.entity.enu.ReservationStatus;
import com.clone.airbnb.exception.AlreadyReservedException;
import com.clone.airbnb.exception.DataDoesNotExistsException;
import com.clone.airbnb.exception.IsPastDateException;
import com.clone.airbnb.exception.UserDoesNotExistsException;
import com.clone.airbnb.repository.ReservationRepository;
import com.clone.airbnb.repository.RoomRepository;
import com.clone.airbnb.repository.UserRepository;
import com.clone.airbnb.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	@Override
	public List<Reservation> listOfHost(String username) {
		return reservationRepository.findAllByRoom_host_usernameOrderByIdDesc(username);
	}
	
	
	
	@Override
	public List<Reservation> listOfGuest(String username) {
		return reservationRepository.findAllByGuest_usernameOrderByIdDesc(username);
	}
	
	
	
	@Override
	public Reservation reserve(String username, Integer roomId, Date checkIn, Date checkOut) {
		Optional<Room> roomOpt = roomRepository.findById(roomId);
		
		if (roomOpt.isPresent()) {
			Room room = roomOpt.get();
			Optional<User> userOpt = userRepository.findByUsername(username);
			
			if (userOpt.isPresent()) {
				User user = userOpt.get();
			
				Calendar c = Calendar.getInstance();
				
				Date date = checkIn;
				
				c.setTime(date);
				
				while(date.compareTo(checkOut) <= 0) {
					if (CalendarObject.isPast(c)) {
						throw new IsPastDateException("Room[" + roomId + "] - 이미 지난 날짜입니다.");
					}
					if (room.isReserved(date)) {
						throw new AlreadyReservedException("Room[" + roomId + "] - 이미 예약된 날짜입니다.");
					}
					c.add(Calendar.DATE, 1);
					date = c.getTime();
				}
				
				Reservation reservation = new Reservation();
				reservation.setRoom(room);
				reservation.setStatus(ReservationStatus.PENDING);
				reservation.setGuest(user);
				reservation.setCheckIn(checkIn);
				reservation.setCheckOut(checkOut);
				
				reservationRepository.save(reservation);
				return reservation;
			} else {
				throw new UserDoesNotExistsException("존재하지 않는 유저입니다.");
			}
		} else {
			throw new DataDoesNotExistsException("방이 존재하지 않습니다.");
		}
	}
	
	
	
	@Override
	public Reservation get(Integer id) {
		Optional<Reservation> opt = reservationRepository.findById(id);
		
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}
	
	
	
	@Override
	public void cancel(Integer id) {
		Optional<Reservation> opt = reservationRepository.findById(id);
		
		if (opt.isPresent()) {
			Reservation reservation = opt.get();
			reservation.setStatus(ReservationStatus.CANCELED);
			reservationRepository.save(reservation);
		} else {
			throw new DataDoesNotExistsException("예약이 존재하지 않습니다.");
		}
	}
	
	
	
	@Override
	public void confirm(Integer id) {
		Optional<Reservation> opt = reservationRepository.findById(id);
		
		if (opt.isPresent()) {
			Reservation reservation = opt.get();
			reservation.setStatus(ReservationStatus.CONFIRMED);
			reservationRepository.save(reservation);
		} else {
			throw new DataDoesNotExistsException("예약이 존재하지 않습니다.");
		}
	}
	
}
