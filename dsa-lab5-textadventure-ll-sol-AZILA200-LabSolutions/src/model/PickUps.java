package model;

public class PickUps {
    private int pickupID;
    private String pickup;

    public PickUps() {
        this.pickupID = 0;
        this.pickup = "";
    }

    public PickUps(int pickupID, String pickup) {
        this.pickupID = pickupID;
        this.pickup = pickup;
    }

    public int getPickupID() {
        return pickupID;
    }

    public void setPickupID(int pickupID) {
        this.pickupID = pickupID;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String CSVFormat(){
        String outputStr = this.pickupID + "," + this.pickup;
        return outputStr;
    }

    @Override
    public String toString() {
        return "PickUps{" +
                "pickupID=" + pickupID +
                ", pickup='" + pickup + '\'' +
                '}';
    }
}
