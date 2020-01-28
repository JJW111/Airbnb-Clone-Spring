package com.clone.airbnb.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.clone.airbnb.annotation.EntityMapping;
import com.clone.airbnb.entity.Reservation;

@EntityMapping(entity=Reservation.class)
public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Integer> {
}

