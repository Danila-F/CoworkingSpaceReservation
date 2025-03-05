import java.util.Map;

public interface BookingService {
    Booking createBooking(User user, Workspace workspace, TimePeriod timePeriod);
    Map<Integer, Booking> getAllBookings();
    Map<Integer, Booking> getUserBookings(User user);
    Booking findBookingForWorkspace(Workspace workspace, TimePeriod timePeriod);
    boolean deleteBooking(Booking booking);
}
