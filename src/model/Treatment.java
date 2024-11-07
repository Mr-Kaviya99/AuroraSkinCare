package model;

public class Treatment {
    private String treatmentName;
    private double price;

    public Treatment(String treatmentName, double price) {
        this.treatmentName = treatmentName;
        this.price = price;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return treatmentName + " (LKR " + price + ")";
    }
}
