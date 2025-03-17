package com.reservationapp.booking;

import com.reservationapp.user.*;
import com.reservationapp.workspace.*;
import com.reservationapp.booking.timeperiod.*;

import java.util.HashMap;
import java.util.Map;

public class BookingService {
    private final BookingsMap bookings = new BookingsMapWithStateSaving();

    private Map<Integer, Booking> getWorkspaceBookings(Workspace workspace) {
        Map<Integer, Booking> workspaceBookings = new HashMap<>();
        for (Map.Entry<Integer, Booking> entry : bookings.getAll().entrySet()) {
            if (entry.getValue().getWorkspace().equals(workspace)) {
                workspaceBookings.put(entry.getKey(), entry.getValue());
            }
        }
        return workspaceBookings;
    }

    public Booking findBookingForWorkspace(Workspace workspace, TimePeriod timePeriod) {
        Map<Integer, Booking> workspaceBookings = getWorkspaceBookings(workspace);
        for (Map.Entry<Integer, Booking> entry : workspaceBookings.entrySet()) {
            Booking booking = entry.getValue();
            if (booking.isBookedAtTime(timePeriod)) {
                return booking;
            }
        }
        return null;
    }

    private Booking addBooking(User user, Workspace workspace, TimePeriod timePeriod) {
        Booking booking = new Booking(bookings.getNextID(), user, workspace, timePeriod);
        try {
            bookings.add(booking);
            return booking;
        } catch (WrongNewBookingIDException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

     public Booking createBooking(User user, Workspace workspace, TimePeriod timePeriod) {
         Booking existingBooking = findBookingForWorkspace(workspace, timePeriod);
         if (existingBooking == null) {
             return addBooking(user, workspace, timePeriod);
         } else {
             return null;
         }
     }

    public Map<Integer, Booking> getAllBookings() {
        return bookings.getAll();
    }

    public Map<Integer, Booking> getUserBookings(User user) {
        Map<Integer, Booking> userBookings = new HashMap<>();
        for (Map.Entry<Integer, Booking> entry : bookings.getAll().entrySet()) {
            if (entry.getValue().getUser().equals(user)) {
                userBookings.put(entry.getKey(), entry.getValue());
            }
        }
        return userBookings;
    }

    public boolean deleteBooking(Booking booking) {
        return bookings.remove(booking.getId());
    }
}
