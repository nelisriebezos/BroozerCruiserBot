package domain;

public class Tank {
    private long id;
    private double amountOfKm;
    private double totalCostOfGas;

    public Tank() {
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

    public double getAmountOfKm() {
        return amountOfKm;
    }

    public void setAmountOfKm(long amountOfKm) {
        this.amountOfKm = amountOfKm;
    }

    public double getTotalCostOfGas() {
        return totalCostOfGas;
    }

    public void setTotalCostOfGas(long totalCostOfGas) {
        this.totalCostOfGas = totalCostOfGas;
    }

    @Override
    public String toString() {
        return "Tank: " + id + "\n" +
                "amountOfKm: " + amountOfKm + "\n" +
                "totalCostOfGas: " + totalCostOfGas;
    }
}
