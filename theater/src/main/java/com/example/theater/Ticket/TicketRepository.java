package com.example.theater.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("select t from Ticket t Where t.id = ?1")
    Optional<Ticket> findTicketById(Long id);

    @Query("select t from Ticket t Where t.id = ?1")
    Ticket findTicketById2(Long id);
}
