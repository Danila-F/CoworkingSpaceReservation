package com.reservationapp.booking.timeperiod;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class TimePeriod implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public TimePeriod(LocalDateTime startTime, LocalDateTime endTime) throws WrongTimePeriodException {
        if (startTime.isAfter(endTime)) {
            throw new WrongTimePeriodException("Start time must be before end time!");
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

    public boolean isPeriodInPeriod(TimePeriod timePeriod) {
        return isInPeriod(timePeriod.getStartTime()) || isInPeriod(timePeriod.getEndTime());
    }

    public boolean IntersectsPeriod(TimePeriod timePeriod) {
        return isPeriodInPeriod(timePeriod) || timePeriod.isPeriodInPeriod(this);
    }

    @Override
    public String toString() {
        return "From: " + startTime.toString() + "; To: " + endTime.toString();
    }
}
