package com.helpdesk.service;

import com.helpdesk.enums.Category;
import com.helpdesk.enums.Priority;
import com.helpdesk.enums.Status;
import com.helpdesk.model.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketService {
    // In-memory collection storing tickets with Ticket ID as key
    private final Map<String, Ticket> ticketMap = new HashMap<>();

    // 1. Create Ticket
    public Ticket createTicket(String id, String title, String description, Category category, Priority priority) 
            throws InvalidTicketException {
        
        if (id == null || id.trim().isEmpty()) {
            throw new InvalidTicketException("Ticket ID cannot be empty.");
        }
        if (ticketMap.containsKey(id.trim())) {
            throw new InvalidTicketException("A ticket with ID '" + id + "' already exists.");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidTicketException("Ticket title cannot be empty.");
        }

        Ticket ticket = new Ticket(id.trim(), title.trim(), description.trim(), category, priority);
        ticketMap.put(ticket.getId(), ticket);
        return ticket;
    }

    // 2. Fetch Ticket by ID
    public Ticket getTicketById(String id) throws InvalidTicketException {
        Ticket ticket = ticketMap.get(id);
        if (ticket == null) {
            throw new InvalidTicketException("No ticket found with ID: " + id);
        }
        return ticket;
    }

    // 3. Update Ticket Status (Enforcing logical transitions)
    public void updateTicketStatus(String id, Status newStatus) throws InvalidTicketException {
        Ticket ticket = getTicketById(id);
        Status currentStatus = ticket.getStatus();

        // Business Rule: Cannot modify a CLOSED ticket
        if (currentStatus == Status.CLOSED) {
            throw new InvalidTicketException("Ticket " + id + " is CLOSED and cannot be modified.");
        }

        // Business Rule: Cannot jump directly from OPEN to CLOSED
        if (currentStatus == Status.OPEN && newStatus == Status.CLOSED) {
            throw new InvalidTicketException("Ticket must be IN_PROGRESS or RESOLVED before closing.");
        }

        ticket.setStatus(newStatus);
    }

    // 4. View All Tickets
    public List<Ticket> getAllTickets() {
        return new ArrayList<>(ticketMap.values());
    }

    // 5. Search by Category
    public List<Ticket> searchByCategory(Category category) {
        List<Ticket> results = new ArrayList<>();
        for (Ticket ticket : ticketMap.values()) {
            if (ticket.getCategory() == category) {
                results.add(ticket);
            }
        }
        return results;
    }
}