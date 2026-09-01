package com.helpdesk.ui;

import com.helpdesk.enums.Category;
import com.helpdesk.enums.Priority;
import com.helpdesk.enums.Status;
import com.helpdesk.model.Ticket;
import com.helpdesk.service.InvalidTicketException;
import com.helpdesk.service.TicketService;

import java.util.List;
import java.util.Scanner;

public class MainApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TicketService ticketService = new TicketService();

    public static void main(String[] args) {
        boolean running = true;
        System.out.println("==============================================");
        System.out.println(" Welcome to Student Helpdesk Support System ");
        System.out.println("==============================================");

        while (running) {
            printMenu();
            int choice = readIntInput("Select an option (1-6): ");

            switch (choice) {
                case 1 -> createTicketUI();
                case 2 -> viewTicketByIdUI();
                case 3 -> updateStatusUI();
                case 4 -> searchByCategoryUI();
                case 5 -> listAllTicketsUI();
                case 6 -> {
                    running = false;
                    System.out.println("\nThank you for using Student Helpdesk System. Goodbye!");
                }
                default -> System.out.println("\n[Error] Invalid choice! Please enter a number between 1 and 6.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Create New Ticket");
        System.out.println("2. View Ticket Details");
        System.out.println("3. Update Ticket Status");
        System.out.println("4. Search Tickets by Category");
        System.out.println("5. View All Tickets");
        System.out.println("6. Exit");
    }

    private static void createTicketUI() {
        System.out.println("\n--- Create Ticket ---");
        System.out.print("Enter Ticket ID (e.g., TCK-101): ");
        String id = scanner.nextLine();

        System.out.print("Enter Issue Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Detailed Description: ");
        String description = scanner.nextLine();

        Category category = selectCategoryUI();
        Priority priority = selectPriorityUI();

        try {
            Ticket ticket = ticketService.createTicket(id, title, description, category, priority);
            System.out.println("\n[Success] Ticket created successfully!");
            System.out.println(ticket);
        } catch (InvalidTicketException e) {
            System.out.println("\n[Validation Error] " + e.getMessage());
        }
    }

    private static void viewTicketByIdUI() {
        System.out.println("\n--- View Ticket ---");
        System.out.print("Enter Ticket ID: ");
        String id = scanner.nextLine();

        try {
            Ticket ticket = ticketService.getTicketById(id.trim());
            System.out.println("\nTicket Details:");
            System.out.println(ticket);
        } catch (InvalidTicketException e) {
            System.out.println("\n[Error] " + e.getMessage());
        }
    }

    private static void updateStatusUI() {
        System.out.println("\n--- Update Ticket Status ---");
        System.out.print("Enter Ticket ID: ");
        String id = scanner.nextLine();

        Status newStatus = selectStatusUI();

        try {
            ticketService.updateTicketStatus(id.trim(), newStatus);
            System.out.println("\n[Success] Status updated to " + newStatus + " for Ticket ID: " + id);
        } catch (InvalidTicketException e) {
            System.out.println("\n[Error] " + e.getMessage());
        }
    }

    private static void searchByCategoryUI() {
        System.out.println("\n--- Search Tickets by Category ---");
        Category category = selectCategoryUI();

        List<Ticket> tickets = ticketService.searchByCategory(category);
        if (tickets.isEmpty()) {
            System.out.println("\nNo tickets found for category: " + category);
        } else {
            System.out.println("\nFound " + tickets.size() + " ticket(s):");
            for (Ticket t : tickets) {
                System.out.println("----------------------------------------------");
                System.out.println(t);
            }
        }
    }

    private static void listAllTicketsUI() {
        System.out.println("\n--- All Tickets ---");
        List<Ticket> tickets = ticketService.getAllTickets();
        if (tickets.isEmpty()) {
            System.out.println("No tickets registered in the system yet.");
        } else {
            for (Ticket t : tickets) {
                System.out.println("----------------------------------------------");
                System.out.println(t);
            }
        }
    }

    // --- Helper Input Methods for Safe Selection ---

    private static Category selectCategoryUI() {
        System.out.println("Select Category:");
        Category[] categories = Category.values();
        for (int i = 0; i < categories.length; i++) {
            System.out.println((i + 1) + ". " + categories[i]);
        }
        int choice = readIntInput("Choice (1-" + categories.length + "): ");
        if (choice < 1 || choice > categories.length) {
            System.out.println("Invalid selection, defaulting to OTHER.");
            return Category.OTHER;
        }
        return categories[choice - 1];
    }

    private static Priority selectPriorityUI() {
        System.out.println("Select Priority:");
        Priority[] priorities = Priority.values();
        for (int i = 0; i < priorities.length; i++) {
            System.out.println((i + 1) + ". " + priorities[i]);
        }
        int choice = readIntInput("Choice (1-" + priorities.length + "): ");
        if (choice < 1 || choice > priorities.length) {
            System.out.println("Invalid selection, defaulting to LOW.");
            return Priority.LOW;
        }
        return priorities[choice - 1];
    }

    private static Status selectStatusUI() {
        System.out.println("Select Target Status:");
        Status[] statuses = Status.values();
        for (int i = 0; i < statuses.length; i++) {
            System.out.println((i + 1) + ". " + statuses[i]);
        }
        int choice = readIntInput("Choice (1-" + statuses.length + "): ");
        if (choice < 1 || choice > statuses.length) {
            System.out.println("Invalid selection, defaulting to IN_PROGRESS.");
            return Status.IN_PROGRESS;
        }
        return statuses[choice - 1];
    }

    private static int readIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("[Invalid Input] Please enter a valid number.");
            }
        }
    }
}