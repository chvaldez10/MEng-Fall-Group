public class EnhancedRide extends Ride {
    protected int vendorId;
    protected int storeAndFwdFlag; // how data is sent, 0 for No, 1 for Yes

    /**
     * Constructs a new {@code EnhancedRide} instance.
     *
     * @param pickupDatetime   the datetime when the ride was picked up
     * @param dropoffDatetime  the datetime when the ride was dropped off
     * @param passengerCount   the number of passengers in the ride
     * @param pickupLongitude  the longitude of the pickup location
     * @param pickupLatitude   the latitude of the pickup location
     * @param dropoffLongitude the longitude of the dropoff location
     * @param dropoffLatitude  the latitude of the dropoff location
     * @param tripDuration     the duration of the trip in minutes
     * @param vendorId         the ID of the vendor
     * @param storeAndFwdFlag  the store and forward flag
     */
    public EnhancedRide(String pickupDatetime, String dropoffDatetime, int passengerCount, double pickupLongitude, double pickupLatitude, double dropoffLongitude, double dropoffLatitude, int tripDuration, int vendorId, int storeAndFwdFlag) {
        super(pickupDatetime, dropoffDatetime, passengerCount, pickupLongitude, pickupLatitude, dropoffLongitude, dropoffLatitude, tripDuration);
        this.vendorId = vendorId;
        this.storeAndFwdFlag = storeAndFwdFlag;
    }

    /**
     * Returns the vendor ID.
     *
     * @return the vendor ID
     */
    public int getVendorId() {
        return vendorId;
    }

    /**
     * Sets the vendor ID.
     *
     * @param vendorId the vendor ID to set
     */
    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    /**
     * Returns the store and forward flag.
     *
     * @return the store and forward flag
     */
    public int isStoreAndFwdFlag() {
        return storeAndFwdFlag;
    }

    /**
     * Sets the store and forward flag.
     *
     * @param storeAndFwdFlag the store and forward flag to set
     */
    public void setStoreAndFwdFlag(int storeAndFwdFlag) {
        this.storeAndFwdFlag = storeAndFwdFlag;
    }
}
