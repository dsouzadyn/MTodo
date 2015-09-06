package io.github.dsouzadyn.mtodo;

/**
 * Created by DYLAN on 09-08-2015.
 */
public class Todos {
    String title, description;
    int priority;

    public Todos(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }
    public String getTitle() {
        return this.title;
    }
    public String getDescription() {
        return this.description;
    }
    public int getPriority() {
        return this.priority;
    }
}
