import java.util.HashSet;
import java.util.Set;

class DefaultSpot {
    private final int spotId;

    DefaultSpot(int spotId) {
        this.spotId = spotId;
    }
}

// Deliberately violates the contract to demonstrate the broken case.
@SuppressWarnings("overrides")
class HalfSpot {
    private final int spotId;

    HalfSpot(int spotId) {
        this.spotId = spotId;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof HalfSpot spot && spot.spotId == spotId;
    }
}

class MutableEqSpot {
    private final int spotId;
    private boolean available = true;

    MutableEqSpot(int spotId) {
        this.spotId = spotId;
    }

    void park() {
        available = false;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof MutableEqSpot spot && spot.available == available;
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(available);
    }
}

class GoodSpot {
    private final int spotId;

    GoodSpot(int spotId) {
        this.spotId = spotId;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return other instanceof GoodSpot spot && spotId == spot.spotId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(spotId);
    }
}

public class Doorway4Demo {
    public static void main(String[] args) {
        Set<DefaultSpot> defaults = new HashSet<>();
        defaults.add(new DefaultSpot(101));
        defaults.add(new DefaultSpot(101));
        System.out.println("Default equality  : size=" + defaults.size() + " (same spot booked twice)");

        Set<HalfSpot> halves = new HashSet<>();
        halves.add(new HalfSpot(101));
        System.out.println("equals w/o hashCode: contains=" + halves.contains(new HalfSpot(101))
                + " (almost always false)");

        Set<MutableEqSpot> mutable = new HashSet<>();
        MutableEqSpot spot = new MutableEqSpot(101);
        mutable.add(spot);
        spot.park();
        System.out.println("Mutable equality  : contains=" + mutable.contains(spot) + " (object lost)");

        Set<GoodSpot> identities = new HashSet<>();
        identities.add(new GoodSpot(101));
        identities.add(new GoodSpot(101));
        System.out.println("Identity equality : size=" + identities.size()
                + " contains=" + identities.contains(new GoodSpot(101)));
    }
}
