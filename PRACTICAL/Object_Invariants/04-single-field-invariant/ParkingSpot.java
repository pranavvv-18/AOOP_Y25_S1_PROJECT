class ParkingSpot {
    private int spotId;

    ParkingSpot(int spotId) {
        if (spotId <= 0) {
            throw new IllegalArgumentException("Spot ID must be positive");
        }
        this.spotId = spotId;
    }

    void displayDetails() {
        System.out.println("Spot ID: " + spotId);
    }

    public static void main(String[] args) {
        ParkingSpot p1 = new ParkingSpot(101);
        p1.displayDetails();
        // ParkingSpot p2 = new ParkingSpot(-101);
    }
}
