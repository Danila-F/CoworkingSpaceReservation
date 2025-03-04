import java.time.LocalDateTime;
import java.util.Map;

public interface BookingService {
    Booking createBooking(User user, Workspace workspace, LocalDateTime startTime, LocalDateTime endTime);
    Map<Integer, Booking> getAllBookings();
    Map<Integer, Booking> getUserBookings(User user);
    Booking findBookingForWorkspace(Workspace workspace, LocalDateTime startTime, LocalDateTime endTime);
}
