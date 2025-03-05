public class Booking {
    private final int id;
    private final User user;
    private final Workspace workspace;
    private final TimePeriod timePeriod;

    public Booking(int id, User user, Workspace workspace, TimePeriod timePeriod) {
        this.id = id;
        this.user = user;
        this.workspace = workspace;
        this.timePeriod = timePeriod;
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

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public boolean isBookedAtTime(TimePeriod timePeriod) {
        if (timePeriod.isInPeriod(timePeriod.getStartTime()) || timePeriod.isInPeriod(timePeriod.getEndTime())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(id) + ". User: " + user.getUsername() +
        "; Workspace: " + workspace.toString() +
        "; " + timePeriod.toString();
    }
}
