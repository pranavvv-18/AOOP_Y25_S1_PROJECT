import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

enum BookingStatus {
    CREATED, CONFIRMED, ACTIVE, COMPLETED, CANCELLED
}

final class Booking {
    private final int bookingId;
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final List<String> notes = new ArrayList<>();
    private BookingStatus status = BookingStatus.CREATED;

    Booking(int bookingId, LocalDateTime start, LocalDateTime end) {
        if (bookingId <= 0) {
            throw new IllegalArgumentException("Invalid booking ID");
        }
        Objects.requireNonNull(start, "Start is required");
        Objects.requireNonNull(end, "End is required");
        if (!end.isAfter(start)) {
            throw new IllegalArgumentException("End must follow start");
        }
        this.bookingId = bookingId;
        this.start = start;
        this.end = end;
    }

    void confirm() {
        if (status != BookingStatus.CREATED) {
            throw new IllegalStateException("Only CREATED can be confirmed");
        }
        status = BookingStatus.CONFIRMED;
    }

    void activate() {
        if (status != BookingStatus.CONFIRMED) {
            throw new IllegalStateException("Only CONFIRMED can be activated");
        }
        status = BookingStatus.ACTIVE;
    }

    void complete() {
        if (status != BookingStatus.ACTIVE) {
            throw new IllegalStateException("Only ACTIVE can be completed");
        }
        status = BookingStatus.COMPLETED;
    }

    void addNote(String note) {
        if (note == null || note.isBlank()) {
            throw new IllegalArgumentException("Note required");
        }
        notes.add(note);
    }

    List<String> getNotes() {
        return List.copyOf(notes);
    }

    BookingStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Booking booking && booking.bookingId == bookingId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(bookingId);
    }

    @Override
    public String toString() {
        return "Booking[" + bookingId + ", " + status + ", " + start + " -> " + end + "]";
    }
}

public class ProtectedBookingDemo {
    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.of(2026, 11, 9, 10, 0);
        Booking booking = new Booking(501, start, start.plusHours(2));
        booking.confirm();
        booking.activate();
        booking.addNote("Level 1, near lift");
        booking.complete();
        System.out.println(booking);
        System.out.println("notes=" + booking.getNotes());

        try {
            booking.activate();
        } catch (IllegalStateException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            booking.getNotes().add("hack");
        } catch (UnsupportedOperationException e) {
            System.out.println("Rejected: notes are read-only");
        }
        try {
            new Booking(502, start, start.minusHours(1));
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}
