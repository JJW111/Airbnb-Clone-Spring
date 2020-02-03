package com.clone.airbnb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.clone.airbnb.annotation.EntityMapping;
import com.clone.airbnb.entity.Reservation;

@EntityMapping(entity=Reservation.class)
public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Integer> {
	Optional<Reservation> findByIdAndGuest_username(Integer id, String username);
	List<Reservation> findAllByRoom_host_usernameOrderByIdDesc(String username);
	List<Reservation> findAllByGuest_usernameOrderByIdDesc(String username);
}

