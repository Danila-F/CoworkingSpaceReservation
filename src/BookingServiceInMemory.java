import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class BookingServiceInMemory implements BookingService {
    private final Map<Integer, Booking> bookings = new TreeMap<>();

    private Map<Integer, Booking> getWorkspaceBookings(Workspace workspace) {
        Map<Integer, Booking> workspaceBookings = new HashMap<>();
        for (Map.Entry<Integer, Booking> entry : bookings.entrySet()) {
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
        TreeMap<Integer, Booking> treeBookings = (TreeMap<Integer, Booking>) bookings;
        int newId = (treeBookings.isEmpty()) ? 0 : treeBookings.lastKey() + 1;
        Booking booking = new Booking(newId, user, workspace, timePeriod);
        bookings.put(newId, booking);
        return booking;
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
        return new TreeMap<>(bookings);
    }

    public Map<Integer, Booking> getUserBookings(User user) {
        Map<Integer, Booking> userBookings = new HashMap<>();
        for (Map.Entry<Integer, Booking> entry : bookings.entrySet()) {
            if (entry.getValue().getUser().equals(user)) {
                userBookings.put(entry.getKey(), entry.getValue());
            }
        }
        return userBookings;
    }

}
