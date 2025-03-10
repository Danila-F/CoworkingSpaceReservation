package com.reservationapp.booking;

import java.io.*;
import java.util.HashMap;

class BookingsMapInFile extends BookingsMapInMemory implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final String FILE_PATH = "bookings.ser";

    public BookingsMapInFile() {
        deserialize();
        Runtime.getRuntime().addShutdownHook(new Thread(this::serialize));
    }

    private void serialize() {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            stream.writeObject(lastBookingID);
            stream.writeObject(bookings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void deserialize() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
                lastBookingID = (Integer) stream.readObject();
                bookings = (HashMap<Integer, Booking>) stream.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }
}
