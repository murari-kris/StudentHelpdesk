package com.helpdesk.service;

public class InvalidTicketException extends Exception {
    public InvalidTicketException(String message) {
        super(message);
    }
}