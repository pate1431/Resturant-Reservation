package ca.sheridancollege.pate1431.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.pate1431.beans.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
