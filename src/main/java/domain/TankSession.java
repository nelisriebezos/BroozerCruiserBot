package domain;

public class TankSession {
    private long id;
    private int amountOfKm;

    public TankSession() {
    }

    public void addAmountOfKm(double amountOfKm) {
        this.amountOfKm += amountOfKm;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAmountOfKm() {
        return amountOfKm;
    }

    public void setAmountOfKm(int amountOfKm) {
        this.amountOfKm = amountOfKm;
    }

    public void addAmountOfKm(int amountOfKm) {
        this.amountOfKm += amountOfKm;
    }

    @Override
    public String toString() {
        return "Tank: " + id + "\n" +
                "amountOfKm: " + amountOfKm;
    }
}
