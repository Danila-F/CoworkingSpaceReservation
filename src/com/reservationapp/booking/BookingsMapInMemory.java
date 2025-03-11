package com.reservationapp.booking;

import java.util.HashMap;
import java.util.Map;

class BookingsMapInMemory implements BookingsMap{
    protected Map<Integer, Booking> bookings = new HashMap<>();
    protected Integer lastBookingID = 0;

    @Override
    public void add(Booking booking) throws WrongNewBookingIDException{
        if (booking.getId() != getNextID()) {
            throw new WrongNewBookingIDException("You're trying to add booking with incorrect ID: " + booking.getId() + ", but it should be: " + getNextID());
        } else {
            lastBookingID = booking.getId();
            bookings.put(lastBookingID, booking);
        }
    }

    @Override
    public Booking get(int bookingID) {
        return bookings.get(bookingID);
    }

    @Override
    public boolean isExist(int bookingID) {
        return bookings.containsKey(bookingID);
    }

    @Override
    public Map<Integer, Booking> getAll() {
        return bookings;
    }

    @Override
    public int getNextID() {
        return isEmpty() ? lastBookingID : lastBookingID + 1;
    }

    @Override
    public boolean isEmpty() {
        return bookings.isEmpty();
    }

    @Override
    public boolean remove(int bookingID) {
        Booking removedBooking = bookings.remove(bookingID);
        if (removedBooking != null) {
            if (removedBooking.getId() == lastBookingID) {
                lastBookingID--;
            }
            return true;
        } else {
            return false;
        }
    }
}
