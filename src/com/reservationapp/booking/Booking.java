package com.reservationapp.booking;

import com.reservationapp.user.*;
import com.reservationapp.workspace.*;
import com.reservationapp.booking.timeperiod.*;

import java.io.Serial;
import java.io.Serializable;

public class Booking implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final int id;
    private final User user;
    private final Workspace workspace;
    private final TimePeriod timePeriod;

    public Booking(int id, User user, Workspace workspace, TimePeriod timePeriod) {
        this.id = id;
        this.user = user;
        this.workspace = workspace;
        this.timePeriod = timePeriod;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public boolean isBookedAtTime(TimePeriod requestedTimePeriod) {
        return timePeriod.IntersectsPeriod(requestedTimePeriod);
    }

    @Override
    public String toString() {
        return String.valueOf(id) + ". User: " + user.getUsername() +
        "; Workspace: " + workspace.toString() +
        "; " + timePeriod.toString();
    }

    public boolean equals(Booking booking) {
        return (this.id == booking.id);
    }
}
