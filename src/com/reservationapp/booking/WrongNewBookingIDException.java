package com.reservationapp.booking;

class WrongNewBookingIDException extends Exception{
    public WrongNewBookingIDException(String message) {
        super(message);
    }
}
