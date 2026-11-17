import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

interface Describable {
    String describe();
}

class Registry {
    void register(Describable describable) {
        System.out.println("Registry sees: " + describable.describe());
    }
}

class LeakyBooking implements Describable {
    private final int bookingId;
    private String status;

    LeakyBooking(int bookingId, Registry registry) {
        registry.register(this);
        this.bookingId = bookingId;
        status = "CREATED";
    }

    @Override
    public String describe() {
        return "id=" + bookingId + ", status=" + status;
    }
}

final class SafeBooking implements Describable {
    private final int bookingId;
    private final String status;

    private SafeBooking(int bookingId) {
        if (bookingId <= 0) {
            throw new IllegalArgumentException("Invalid booking ID");
        }
        this.bookingId = bookingId;
        status = "CREATED";
    }

    static SafeBooking create(int bookingId, Registry registry) {
        Objects.requireNonNull(registry, "Registry is required");
        SafeBooking booking = new SafeBooking(bookingId);
        registry.register(booking);
        return booking;
    }

    @Override
    public String describe() {
        return "id=" + bookingId + ", status=" + status;
    }
}

class BaseBooking {
    BaseBooking() {
        init();
    }

    protected void init() {
    }
}

class PremiumBooking extends BaseBooking {
    private final List<String> perks = new ArrayList<>();

    @Override
    protected void init() {
        perks.add("VALET");
    }
}

public class Doorway5Demo {
    public static void main(String[] args) {
        Registry registry = new Registry();

        System.out.println("--- Broken: leaking this ---");
        new LeakyBooking(501, registry);

        System.out.println("--- Fixed: static factory ---");
        SafeBooking.create(502, registry);

        System.out.println("--- Broken: overridable method in constructor ---");
        try {
            new PremiumBooking();
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: subclass field was not initialised yet");
        }
    }
}
