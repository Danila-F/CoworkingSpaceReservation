import java.time.LocalDateTime;

public class Booking {
    private final int id;
    private final User user;
    private final Workspace workspace;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Booking(int id, User user, Workspace workspace, LocalDateTime starTime, LocalDateTime endTime) {
        this.id = id;
        this.user = user;
        this.workspace = workspace;
        this.startTime = starTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public boolean isBookedAtTime(LocalDateTime fromTime, LocalDateTime toTime) {
        boolean isFromTimeInPeriod = !fromTime.isBefore(startTime) && !fromTime.isAfter(endTime);
        boolean isToTimeInPeriod = !toTime.isBefore(startTime) && !toTime.isAfter(endTime);
        
        if (isFromTimeInPeriod || isToTimeInPeriod) {
            return true;
        } else {
            return false;
        }
    }
}
