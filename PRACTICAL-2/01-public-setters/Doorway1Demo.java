class BadSpot {
    private int spotId;
    private boolean available = true;
    private String vehicleNumber;

    BadSpot(int spotId) {
        if (spotId <= 0) {
            throw new IllegalArgumentException("Invalid spot ID");
        }
        this.spotId = spotId;
    }

    void setSpotId(int spotId) {
        this.spotId = spotId;
    }

    void setAvailable(boolean available) {
        this.available = available;
    }

    void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    @Override
    public String toString() {
        return "BadSpot[id=" + spotId + ", available=" + available
                + ", vehicle=" + vehicleNumber + "]";
    }
}

class SafeSpot {
    private final int spotId;
    private boolean available = true;
    private String vehicleNumber;

    SafeSpot(int spotId) {
        if (spotId <= 0) {
            throw new IllegalArgumentException("Invalid spot ID");
        }
        this.spotId = spotId;
    }

    void park(String vehicleNumber) {
        if (!available) {
            throw new IllegalStateException("Spot already occupied");
        }
        if (vehicleNumber == null || vehicleNumber.isBlank()) {
            throw new IllegalArgumentException("Vehicle number required");
        }
        this.vehicleNumber = vehicleNumber;
        available = false;
    }

    void release() {
        if (available) {
            throw new IllegalStateException("Spot is already free");
        }
        vehicleNumber = null;
        available = true;
    }

    @Override
    public String toString() {
        return "SafeSpot[id=" + spotId + ", available=" + available
                + ", vehicle=" + vehicleNumber + "]";
    }
}

public class Doorway1Demo {
    public static void main(String[] args) {
        System.out.println("--- Broken: public setters ---");
        BadSpot bad = new BadSpot(101);
        bad.setSpotId(-5);
        bad.setVehicleNumber("TS09AB1234");
        System.out.println(bad);

        System.out.println("--- Fixed: behaviour methods ---");
        SafeSpot safe = new SafeSpot(101);
        safe.park("TS09AB1234");
        System.out.println(safe);
        try {
            safe.park("TS10XY0001");
        } catch (IllegalStateException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        safe.release();
        System.out.println(safe);
    }
}
