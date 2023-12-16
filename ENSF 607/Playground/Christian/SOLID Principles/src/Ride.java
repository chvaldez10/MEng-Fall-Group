/**
 * The Ride class represents a ride in a vehicle sharing service.
 * It includes details such as pickup and dropoff times and locations,
 * the number of passengers, and the trip duration.
 */

public class Ride {
    protected String pickupDatetime;
    protected String dropoffDatetime;
    protected int passengerCount;
    protected double pickupLongitude;
    protected double pickupLatitude;
    protected double dropoffLongitude;
    protected double dropoffLatitude;
    protected int tripDuration;

    /**
     * Constructs a new {@code Ride} instance.
     *
     * @param pickupDatetime   the datetime when the ride was picked up
     * @param dropoffDatetime  the datetime when the ride was dropped off
     * @param passengerCount   the number of passengers in the ride
     * @param pickupLongitude  the longitude of the pickup location
     * @param pickupLatitude   the latitude of the pickup location
     * @param dropoffLongitude the longitude of the dropoff location
     * @param dropoffLatitude  the latitude of the dropoff location
     * @param tripDuration     the duration of the trip in minutes
     */
    public Ride(String pickupDatetime, String dropoffDatetime, int passengerCount,
                double pickupLongitude, double pickupLatitude, double dropoffLongitude,
                double dropoffLatitude, int tripDuration) {
        this.pickupDatetime = pickupDatetime;
        this.dropoffDatetime = dropoffDatetime;
        this.passengerCount = passengerCount;
        this.pickupLongitude = pickupLongitude;
        this.pickupLatitude = pickupLatitude;
        this.dropoffLongitude = dropoffLongitude;
        this.dropoffLatitude = dropoffLatitude;
        this.tripDuration = tripDuration;
    }

    /**
     * Returns the pickup datetime of this ride.
     *
     * @return the pickup datetime
     */
    public String getPickupDatetime() {
        return pickupDatetime;
    }

    /**
     * Sets the pickup datetime of this ride.
     *
     * @param pickupDatetime the pickup datetime to set
     */
    public void setPickupDatetime(String pickupDatetime) {
        this.pickupDatetime = pickupDatetime;
    }

    /**
     * Returns the dropoff datetime of this ride.
     *
     * @return the dropoff datetime
     */
    public String getDropoffDatetime() {
        return dropoffDatetime;
    }

    /**
     * Sets the dropoff datetime of this ride.
     *
     * @param dropoffDatetime the dropoff datetime to set
     */
    public void setDropoffDatetime(String dropoffDatetime) {
        this.dropoffDatetime = dropoffDatetime;
    }

    /**
     * Returns the passenger count of this ride.
     *
     * @return the passenger count
     */
    public int getPassengerCount() {
        return passengerCount;
    }

    /**
     * Sets the passenger count of this ride.
     *
     * @param passengerCount the passenger count to set
     */
    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }

    /**
     * Returns the pickup longitude of this ride.
     *
     * @return the pickup longitude
     */
    public double getPickupLongitude() {
        return pickupLongitude;
    }

    /**
     * Sets the pickup longitude of this ride.
     *
     * @param pickupLongitude the pickup longitude to set
     */
    public void setPickupLongitude(double pickupLongitude) {
        this.pickupLongitude = pickupLongitude;
    }

    /**
     * Returns the pickup latitude of this ride.
     *
     * @return the pickup latitude
     */
    public double getPickupLatitude() {
        return pickupLatitude;
    }

    /**
     * Sets the pickup latitude of this ride.
     *
     * @param pickupLatitude the pickup latitude to set
     */
    public void setPickupLatitude(double pickupLatitude) {
        this.pickupLatitude = pickupLatitude;
    }

    /**
     * Returns the dropoff longitude of this ride.
     *
     * @return the dropoff longitude
     */
    public double getDropoffLongitude() {
        return dropoffLongitude;
    }

    /**
     * Sets the dropoff longitude of this ride.
     *
     * @param dropoffLongitude the dropoff longitude to set
     */
    public void setDropoffLongitude(double dropoffLongitude) {
        this.dropoffLongitude = dropoffLongitude;
    }

    /**
     * Returns the dropoff latitude of this ride.
     *
     * @return the dropoff latitude
     */
    public double getDropoffLatitude() {
        return dropoffLatitude;
    }

    /**
     * Sets the dropoff latitude of this ride.
     *
     * @param dropoffLatitude the dropoff latitude to set
     */
    public void setDropoffLatitude(double dropoffLatitude) {
        this.dropoffLatitude = dropoffLatitude;
    }

    /**
     * Returns the trip duration of this ride.
     *
     * @return the trip duration
     */
    public int getTripDuration() {
        return tripDuration;
    }

    /**
     * Sets the trip duration of this ride.
     *
     * @param tripDuration the trip duration to set
     */
    public void setTripDuration(int tripDuration) {
        this.tripDuration = tripDuration;
    }
}
