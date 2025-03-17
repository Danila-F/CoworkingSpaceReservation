package com.reservationapp.booking;

class BookingsMapWithStateSaving extends BookingsMapInMemory {

    public BookingsMapWithStateSaving() {
        BookingsRepository bookingsRepository = new BookingsRepository(this);
        bookingsRepository.load();
        Runtime.getRuntime().addShutdownHook(new Thread(bookingsRepository::save));
    }
}
