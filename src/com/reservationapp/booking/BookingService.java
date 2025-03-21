package com.reservationapp.booking;

import com.reservationapp.user.*;
import com.reservationapp.workspace.*;
import com.reservationapp.booking.timeperiod.*;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookingService {
    private final BookingsMap bookings = new BookingsMapWithStateSaving();

    private Map<Integer, Booking> getWorkspaceBookings(Workspace workspace) {
        return bookings.getAll().entrySet().stream()
                .filter(entry -> entry.getValue().getWorkspace().equals(workspace))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Optional<Booking> findBookingForWorkspace(Workspace workspace, TimePeriod timePeriod) {
        return getWorkspaceBookings(workspace).values().stream()
                .filter(booking -> booking.isBookedAtTime(timePeriod))
                .findFirst();
    }

    private Optional<Booking> addBooking(User user, Workspace workspace, TimePeriod timePeriod) {
        Booking booking = new Booking(bookings.getNextID(), user, workspace, timePeriod);
        try {
            bookings.add(booking);
            return Optional.of(booking);
        } catch (WrongNewBookingIDException ex) {
            System.out.println(ex.getMessage());
            return Optional.empty();
        }
    }

    public Optional<Booking> createBooking(User user, Workspace workspace, TimePeriod timePeriod) {
        if (findBookingForWorkspace(workspace, timePeriod).isPresent()) {
            return Optional.empty();
        } else {
            return addBooking(user, workspace, timePeriod);
        }
    }

    public Map<Integer, Booking> getAllBookings() {
        return bookings.getAll();
    }

    public Map<Integer, Booking> getUserBookings(User user) {
        return bookings.getAll().entrySet().stream()
                .filter(entry -> entry.getValue().getUser().equals(user))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public boolean deleteBooking(Booking booking) {
        return bookings.remove(booking.getId());
    }
}
