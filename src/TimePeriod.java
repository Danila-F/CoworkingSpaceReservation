import java.time.LocalDateTime;

public class TimePeriod {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    
    public TimePeriod(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time!");
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public boolean isInPeriod(LocalDateTime time) {
        return !time.isBefore(startTime) && !time.isAfter(endTime);
    }

    @Override
    public String toString() {
        return "From: " + startTime.toString() + "; To: " + endTime.toString();
    }
}
