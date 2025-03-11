package com.reservationapp.booking;

import java.util.Map;

interface BookingsMap {
    void add(Booking booking) throws WrongNewBookingIDException;
    Booking get(int bookingID);
    boolean isExist(int bookingID);
    Map<Integer, Booking> getAll();
    int getNextID();
    boolean isEmpty();
    boolean remove(int bookingID);
}
