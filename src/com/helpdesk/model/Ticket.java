package com.helpdesk.model;

import com.helpdesk.enums.Category;
import com.helpdesk.enums.Priority;
import com.helpdesk.enums.Status;
import java.time.LocalDateTime;

public class Ticket {
    private String id;
    private String title;
    private String description;
    private Category category;
    private Priority priority;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Ticket(String id, String title, String description, Category category, Priority priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.status = Status.OPEN;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; touch(); }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; touch(); }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; touch(); }

    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; touch(); }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; touch(); }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    private void touch() {
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | Priority: %s | Status: %s | Category: %s\nDescription: %s\nCreated: %s",
                id, title, priority, status, category, description, createdAt.toString().substring(0, 19));
    }
}