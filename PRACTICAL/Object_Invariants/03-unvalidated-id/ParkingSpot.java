class ParkingSpot {
    private final int spotId;

    ParkingSpot(int spotId) {
        if (spotId <= 0) {
            throw new IllegalArgumentException("Spot ID must be positive");
        }
        this.spotId = spotId;
    }

    public static void main(String[] args) {
        ParkingSpot p1 = new ParkingSpot(101);
        System.out.println("Spot ID: " + p1.spotId);

        try {
            new ParkingSpot(-101);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}
