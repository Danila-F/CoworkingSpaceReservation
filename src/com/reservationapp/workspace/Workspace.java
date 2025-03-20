package com.reservationapp.workspace;

import java.io.Serial;
import java.io.Serializable;

public class Workspace implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final int id;
    private String description;

    public Workspace(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.valueOf(id) + ". " + description;
    }

    public boolean equals(Workspace workspace) {
        return (this.id == workspace.id);
    }
}
