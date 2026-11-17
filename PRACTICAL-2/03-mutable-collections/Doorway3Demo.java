import java.util.ArrayList;
import java.util.List;

class BadWallet {
    private long balance;
    private final List<Long> credits = new ArrayList<>();

    void credit(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        balance += amount;
        credits.add(amount);
    }

    List<Long> getCredits() {
        return credits;
    }

    boolean isConsistent() {
        return balance == credits.stream().mapToLong(Long::longValue).sum();
    }
}

class SafeWallet {
    private long balance;
    private final List<Long> credits = new ArrayList<>();

    void credit(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        balance = Math.addExact(balance, amount);
        credits.add(amount);
    }

    List<Long> getCredits() {
        return List.copyOf(credits);
    }

    boolean isConsistent() {
        return balance == credits.stream().mapToLong(Long::longValue).sum();
    }
}

class ParkingLot {
    private final List<String> spotIds;

    ParkingLot(List<String> spotIds) {
        this.spotIds = new ArrayList<>(spotIds);
    }

    int size() {
        return spotIds.size();
    }
}

public class Doorway3Demo {
    public static void main(String[] args) {
        System.out.println("--- Broken: exposed list ---");
        BadWallet bad = new BadWallet();
        bad.credit(500);
        bad.getCredits().clear();
        System.out.println("consistent=" + bad.isConsistent());

        System.out.println("--- Fixed: List.copyOf ---");
        SafeWallet safe = new SafeWallet();
        safe.credit(500);
        try {
            safe.getCredits().clear();
        } catch (UnsupportedOperationException e) {
            System.out.println("Blocked: list is read-only");
        }
        System.out.println("consistent=" + safe.isConsistent() + " credits=" + safe.getCredits());

        System.out.println("--- Copy on input ---");
        List<String> mine = new ArrayList<>(List.of("A1", "A2"));
        ParkingLot lot = new ParkingLot(mine);
        mine.add("A3");
        System.out.println("lot size=" + lot.size() + " (still 2)");
    }
}
