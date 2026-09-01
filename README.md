# Student Helpdesk System

A Java-based console application designed to manage student support tickets through a structured, menu-driven workflow.

## Project Overview

The Student Helpdesk System allows users to create, update, search, track, and resolve support tickets. It provides a simple and organized way to manage student issues based on their category, priority, and current status.

## Features

* Create new support tickets
* Update existing tickets
* Search and view tickets
* Track ticket status
* Resolve and close tickets
* Category-based ticket management
* Priority handling
* Input validation
* Duplicate ticket ID prevention
* Logical ticket status management
* Menu-driven console interface

## Technologies Used

* **Java**
* **Core Java**
* **Object-Oriented Programming (OOP)**
* **Java Collections**
* **Exception Handling**

## Project Structure

```text
src/
└── com/helpdesk/
    ├── enums/
    │   ├── Category.java
    │   ├── Priority.java
    │   └── Status.java
    ├── model/
    │   └── Ticket.java
    ├── service/
    │   ├── TicketService.java
    │   └── InvalidTicketException.java
    └── ui/
        └── MainApp.java
```

## Key Concepts Demonstrated

* Classes and Objects
* Encapsulation
* Enums
* Collections
* Exception Handling
* Input Validation
* Modular Programming
* Separation of Responsibilities

## How to Run

1. Clone the repository.
2. Open the project in Eclipse or any Java IDE.
3. Compile the project.
4. Run `MainApp.java`.
5. Follow the options displayed in the console menu.

## Purpose

This project was developed as a beginner-level Java project to demonstrate practical application of Java fundamentals, OOP concepts, collections, validation, and modular program design.
