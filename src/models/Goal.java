package models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Goal implements Serializable {
    private String title;
    private LocalDateTime deadline;
    private boolean isDone;

    public Goal(String title, LocalDateTime deadline) {
        this.title = title;
        this.deadline = deadline;
        this.isDone = false;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markDone() {
        isDone = true;
    }

    public void setDeadline(LocalDateTime newDeadline) {
        this.deadline = newDeadline;
    }

    @Override
    public String toString() {
        String status = isDone ? "✅ Done" : "❌ Not Done";
        return "Goal: " + title + " | Deadline: " + deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + " | Status: " + status;
    }
}
