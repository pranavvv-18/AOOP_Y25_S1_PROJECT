import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

class BadBooking {
    private final Date start;
    private final Date end;

    BadBooking(Date start, Date end) {
        if (!end.after(start)) {
            throw new IllegalArgumentException("End must be after start");
        }
        this.start = start;
        this.end = end;
    }

    Date getStart() {
        return start;
    }

    Date getEnd() {
        return end;
    }
}

class CopyingBooking {
    private final Date start;
    private final Date end;

    CopyingBooking(Date start, Date end) {
        Date startCopy = new Date(Objects.requireNonNull(start, "Start is required").getTime());
        Date endCopy = new Date(Objects.requireNonNull(end, "End is required").getTime());
        if (!endCopy.after(startCopy)) {
            throw new IllegalArgumentException("End must be after start");
        }
        this.start = startCopy;
        this.end = endCopy;
    }

    Date getStart() {
        return new Date(start.getTime());
    }

    Date getEnd() {
        return new Date(end.getTime());
    }
}

class TimeBooking {
    private final LocalDateTime start;
    private final LocalDateTime end;

    TimeBooking(LocalDateTime start, LocalDateTime end) {
        Objects.requireNonNull(start, "Start is required");
        Objects.requireNonNull(end, "End is required");
        if (!end.isAfter(start)) {
            throw new IllegalArgumentException("End must be after start");
        }
        this.start = start;
        this.end = end;
    }

    LocalDateTime getStart() {
        return start;
    }

    LocalDateTime getEnd() {
        return end;
    }
}

public class Doorway2Demo {
    public static void main(String[] args) {
        System.out.println("--- Broken: escape on the way out ---");
        BadBooking bad = new BadBooking(new Date(1000), new Date(5000));
        bad.getEnd().setTime(0);
        System.out.println("start=" + bad.getStart().getTime()
                + " end=" + bad.getEnd().getTime()
                + " valid=" + bad.getEnd().after(bad.getStart()));

        System.out.println("--- Broken: escape on the way in ---");
        Date callerEnd = new Date(5000);
        BadBooking bad2 = new BadBooking(new Date(1000), callerEnd);
        callerEnd.setTime(0);
        System.out.println("end=" + bad2.getEnd().getTime());

        System.out.println("--- Fix 1: defensive copies ---");
        Date callerEnd2 = new Date(5000);
        CopyingBooking safe = new CopyingBooking(new Date(1000), callerEnd2);
        callerEnd2.setTime(0);
        safe.getEnd().setTime(0);
        System.out.println("end=" + safe.getEnd().getTime());

        System.out.println("--- Fix 2: immutable LocalDateTime ---");
        TimeBooking time = new TimeBooking(
                LocalDateTime.of(2026, 11, 9, 10, 0),
                LocalDateTime.of(2026, 11, 9, 12, 0));
        System.out.println("end=" + time.getEnd() + " (no setter exists, nothing to attack)");
    }
}
