package DataBase.Entities;

/**
 * order entity
 */
public class Order {
    private int id;
    private Direction direction;
    private int quantity;
    private boolean baggage;
    private boolean priorityQueue;
    private User client;
    private double summa;
    private boolean isPaid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isBaggage() {
        return baggage;
    }

    public void setBaggage(boolean baggage) {
        this.baggage = baggage;
    }

    public boolean isPriorityQueue() {
        return priorityQueue;
    }

    public void setPriorityQueue(boolean priorityQueue) {
        this.priorityQueue = priorityQueue;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public double getSumma() {
        return summa;
    }

    public void setSumma(double summa) {
        this.summa = summa;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", direction=" + direction +
                ", quantity=" + quantity +
                ", baggage=" + baggage +
                ", priorityQueue=" + priorityQueue +
                ", client=" + client +
                ", summa=" + summa +
                ", isPaid=" + isPaid +
                '}';
    }
}
