package com.example.theater.Ticket;

import com.example.theater.Employee.Employee;
import com.example.theater.Movie.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(Long ticketId) {
        return ticketRepository.findTicketById2(ticketId);
    }

    public void addNewTicket(Ticket ticket) {
        Optional<Ticket> ticketOptional = ticketRepository.findTicketById(ticket.getId());
    }

    public void deleteTicket(Long ticketId) {
        boolean exists = ticketRepository.existsById(ticketId);
        if (!exists) {
            throw new IllegalStateException(("ticket with id "  + ticketId + " does not exist"));
        }
        ticketRepository.deleteById(ticketId);
    }

    @Transactional
    public void updateTicket(Long ticketId, String holder) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new IllegalStateException("ticket with id " + ticketId + " does not exist"));
        if (holder != null) {
            ticket.setHolder(holder);
        }
    }
}
