package model;
/**
 * @author K2460782
 */
public class Appointment {
    private static int id = 1;
    private int appointmentId;
    private Patient patient;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Appointment.id = id;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    private Doctor doctor;
    private String date;
    private String timeSlot;
    private Treatment treatment;
    private double totalFee;

    public Appointment(Patient patient, Doctor doctor, String date, String timeSlot, Treatment treatment, double totalFee) {
        this.appointmentId = id++;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.timeSlot = timeSlot;
        this.treatment = treatment;
        this.totalFee = totalFee;
    }

    public int getAppointmentID() {
        return appointmentId;
    }

    public String getDate() {
        return date;
    }

    public void displayDetails() {
        System.out.println("Appointment ID: " + appointmentId);
        System.out.println("Patient Name: " + patient.getPatientName());
        System.out.println("Doctor: " + doctor.getDoctorName());
        System.out.println("Date: " + date);
        System.out.println("Time: " + timeSlot);
        System.out.println("Treatment: " + treatment);
        System.out.println("Total Fee (with Tax): " + totalFee);
    }
}
