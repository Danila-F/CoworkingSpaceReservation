package com.reservationapp.booking;

import java.io.*;
import java.util.HashMap;

class BookingsRepository implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final String FILE_PATH = "bookings.ser";
    private final BookingsMapWithStateSaving bookingsMap;

    public BookingsRepository(BookingsMapWithStateSaving bookingsMap) {
        this.bookingsMap = bookingsMap;
    }

    void save() {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            stream.writeObject(bookingsMap.lastBookingID);
            stream.writeObject(bookingsMap.bookings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    void load() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
                bookingsMap.lastBookingID = (Integer) stream.readObject();
                bookingsMap.bookings = (HashMap<Integer, Booking>) stream.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }
}
