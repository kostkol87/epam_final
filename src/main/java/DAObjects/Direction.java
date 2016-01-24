package DAObjects;

import java.util.Date;

public class Direction {
    private int id;
    private String departure;
    private Date depTime;
    private String destination;
    private Date destTime;
    private double basicPrice;
    private double dateMultiplier;
    private double fillMultiplier;
    private int capacity;
    private int leftPlaces;
    private static final int DAY = 1000*60*60*24;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public Date getDepTime() {
        return depTime;
    }

    public void setDepTime(Date depTime) {
        this.depTime = depTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDestTime() {
        return destTime;
    }

    public void setDestTime(Date destTime) {
        this.destTime = destTime;
    }

    public double getBasicPrice() {

        return Math.round(basicPrice    );
    }

    public void setBasicPrice(double basicPrice) {
        /**
         * if there is less than 30 days on departure, price become bigger
         * if there are less than 50% places aboard lef, price become bigger
         */
        long today = new Date().getTime();
        double price = basicPrice;
        if ((depTime.getTime() - today)/DAY < 30){
            price += (dateMultiplier-1)*basicPrice;

        }
        if((double)leftPlaces/(double)capacity < 0.5){
            price += (fillMultiplier-1)*basicPrice;
        }
        this.basicPrice = price;
    }

    public double getDateMultiplier() {
        return dateMultiplier;
    }

    public void setDateMultiplier(double dateMultiplier) {
        this.dateMultiplier = dateMultiplier;
    }

    public double getFillMultiplier() {
        return fillMultiplier;
    }

    public void setFillMultiplier(double fillMultiplier) {
        this.fillMultiplier = fillMultiplier;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getLeftPlaces() {
        return leftPlaces;
    }

    public void setLeftPlaces(int leftPlaces) {
        this.leftPlaces = leftPlaces;
    }


    @Override
    public String toString() {
        return "Direction{" +
                "id=" + id +
                ", departure='" + departure + '\'' +
                ", depTime=" + depTime +
                ", destination='" + destination + '\'' +
                ", destTime=" + destTime +
                ", basicPrice=" + basicPrice +
                ", dateMultiplier=" + dateMultiplier +
                ", fillMultiplier=" + fillMultiplier +
                ", capacity=" + capacity +
                ", leftPlaces=" + leftPlaces +
                '}';
    }
}
