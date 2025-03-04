import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class BookingServiceInMemory {
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

    private Booking findBookingForWorkspace(Workspace workspace, LocalDateTime startTime, LocalDateTime endTime) {
        Map<Integer, Booking> workspaceBookings = getWorkspaceBookings(workspace);
        for (Map.Entry<Integer, Booking> entry : workspaceBookings.entrySet()) {
            Booking booking = entry.getValue();
            if (booking.isBookedAtTime(startTime, endTime)) {
                return booking;
            }
        }
        return null;
    }

    // private Booking createBooking(User user, Workspace workspace, LocalDateTime startTime, LocalDateTime endTime) {
    //     boolean isAppropriate = true;
    //     Booking existingBooking = findBookingForWorkspace(workspace, startTime, endTime);
        
    // }

    public Booking addBooking(User user, Workspace workspace, LocalDateTime startTime, LocalDateTime endTime) {
        TreeMap<Integer, Booking> treeBookings = (TreeMap<Integer, Booking>) bookings;
        int newId = (treeBookings.isEmpty()) ? 0 : treeBookings.lastKey() + 1;
        Booking booking = new Booking(newId, user, workspace, startTime, endTime);
        bookings.put(newId, booking);
        return booking;
    }

    private Map<Integer, Booking> getAllBookings() {
        return new TreeMap<>(bookings);
    }

    private Map<Integer, Booking> getUserBookings(User user) {
        Map<Integer, Booking> userBookings = new HashMap<>();
        for (Map.Entry<Integer, Booking> entry : bookings.entrySet()) {
            if (entry.getValue().getUser().equals(user)) {
                userBookings.put(entry.getKey(), entry.getValue());
            }
        }
        return userBookings;
    }

    public Map<Integer, Booking> getBookings(User user) {
        return user.getIsAdmin() ? getAllBookings() : getUserBookings(user);
    }

}
