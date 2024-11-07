package service.impl;
/**
 * @author : K***
 */

import model.Appointment;
import model.Doctor;
import model.Patient;
import model.Treatment;
import service.AppointmentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppointmentServiceImpl implements AppointmentService {
    private List<Doctor> doctors = new ArrayList<>();
    private List<Treatment> treatments = new ArrayList<>();
    private List<Appointment> appointments = new ArrayList<>();
    Patient patient = new Patient();
    Doctor doctor = new Doctor();

    /**
     * Initialize default data
     */
    public AppointmentServiceImpl() {
        addDoctors();
        addTreatments();
    }

    /**
     * Initialize Doctors
     */
    private void addDoctors() {
        doctors.add(new Doctor("Doctor 1"));
        doctors.add(new Doctor("Doctor 2"));
    }

    /**
     * Initialize Treatments
     */
    private void addTreatments() {
        treatments.add(new Treatment("Acne Treatment", 2750.00));
        treatments.add(new Treatment("Skin Whitening", 7650.00));
        treatments.add(new Treatment("Mole Removal", 3850.00));
        treatments.add(new Treatment("Laser Treatment", 12500.00));
    }

    /**
     * Add patient details
     *
     * @param scanner to get inputs
     */
    void addPatientDetails(Scanner scanner) {

        System.out.println("-------------------------------------------------------");
        System.out.println("\t\tPatient Details");
        System.out.println("-------------------------------------------------------");
        System.out.print("\t\t* Enter Patient NIC\t\t\t: ");
        String patientNIC = scanner.nextLine();
        System.out.print("\t\t* Enter Patient Name\t\t: ");
        String patientName = scanner.nextLine();
        System.out.print("\t\t* Enter Patient Email\t\t: ");
        String patientEmail = scanner.nextLine();
        System.out.print("\t\t* Enter Patient Telephone\t: ");
        String patientTelephone = scanner.nextLine();
        patient = new Patient(patientNIC, patientName, patientEmail, patientTelephone);
        System.out.println();
    }

    /**
     * View available treatments
     */
    public void viewTreatments() {
        System.out.println("-------------------------------------------------------");
        System.out.println("\t\tAvailable Treatments");
        System.out.println("-------------------------------------------------------");
        for (int i = 0; i < treatments.size(); i++) {
            System.out.println("\t\t" + (i + 1) + ". " + treatments.get(i));
        }
    }

    /**
     * Make new appointment
     *
     * @param scanner to get inputs
     */
    @Override
    public void makeAppointment(Scanner scanner) {

        System.out.println("=======================================================");
        System.out.println("\t\tMake Appointment");
        System.out.println("=======================================================");

        System.out.println("-------------------------------------------------------");
        System.out.println("\t\tDoctor Selection");
        System.out.println("-------------------------------------------------------");
        for (int i = 0; i < doctors.size(); i++) {
            System.out.println("\t\t" + (i + 1) + ". " + doctors.get(i).getDoctorName());
        }
        System.out.print("\t\tEnter your selection\t\t: ");
        int doctorChoice = scanner.nextInt();
        scanner.nextLine();
        Doctor doctor = doctors.get(doctorChoice - 1);

        List<String> availableDays = doctor.getAvailableDays();
        System.out.println("-------------------------------------------------------");
        System.out.println("\t\tAvailable Days");
        System.out.println("-------------------------------------------------------");
        for (int i = 0; i < availableDays.size(); i++) {
            System.out.println("\t\t" + (i + 1) + ". " + availableDays.get(i));
        }
        System.out.print("\t\tEnter your preferred day number\t: ");
        int dayChoice = scanner.nextInt();
        scanner.nextLine();
        String day = availableDays.get(dayChoice - 1);

        List<String> availableSlots = doctor.getAvailableSlots(day);
        System.out.println("-------------------------------------------------------");
        System.out.println("\t\tAvailable Time Slots");
        System.out.println("-------------------------------------------------------");
        for (int i = 0; i < availableSlots.size(); i++) {
            System.out.println("\t\t" + (i + 1) + ". " + availableSlots.get(i));
        }
        System.out.print("\t\tEnter preferred time slot number\t: ");
        int slotChoice = scanner.nextInt();
        scanner.nextLine();
        String timeSlot = availableSlots.get(slotChoice - 1);

        // Check if the chosen time slot is actually available
        if (!availableSlots.contains(timeSlot)) {
            System.out.println("** Selected time slot is not available. Please choose a valid time slot. **");
            return;
        }

        // Book the time slot
        doctor.bookedTime(day, timeSlot);

        viewTreatments();
        System.out.print("\t\tEnter treatment number : ");
        int treatmentChoice = scanner.nextInt();
        scanner.nextLine();

        if (treatmentChoice < 1 || treatmentChoice > treatments.size()) {
            System.out.println("\t\tInvalid treatment choice. Appointment cannot be created.");
            return;
        }

        Treatment selectedTreatment = treatments.get(treatmentChoice - 1);
        double basePrice = selectedTreatment.getPrice();
        double totalFee = basePrice + basePrice * (2.5 / 100);

        addPatientDetails(scanner);

        System.out.println("\t\tRegistration fee\t\t\t: LKR. 500");
        System.out.println("\t\t1. Paid");
        System.out.println("\t\t2. Cancel");
        System.out.println("-------------------------------------------------------");
        System.out.print("\t\tEnter your selection : ");
        int paidOrCancel = scanner.nextInt();

        if (paidOrCancel == 1) {
            Appointment appointment = new Appointment(patient, doctor, day, timeSlot, selectedTreatment, totalFee);
            appointments.add(appointment);
            System.out.println("\n\t\tAppointment successfully created!\n\t\tYour appointment details :");
            printAppointmentDetails(appointment);
        } else {
            System.out.println("\t\tAppointment cancelled");
        }

    }

    @Override
    public void viewAppointmentsByDate(Scanner scanner) {

        System.out.println("=======================================================");
        System.out.println("\t\tView Appointments by Date");
        System.out.println("=======================================================");

        System.out.println("-------------------------------------------------------");
        System.out.println("\t\tDoctor Selection");
        System.out.println("-------------------------------------------------------");
        for (int i = 0; i < doctors.size(); i++) {
            System.out.println("\t\t" + (i + 1) + ". " + doctors.get(i).getDoctorName());
        }
        System.out.print("\t\tEnter your selection\t\t: ");
        int doctorChoice = scanner.nextInt();
        scanner.nextLine();
        Doctor doctor = doctors.get(doctorChoice - 1);

        List<String> availableDays = doctor.getAvailableDays();
        System.out.println("-------------------------------------------------------");
        System.out.println("\t\tAvailable Days");
        System.out.println("-------------------------------------------------------");
        for (int i = 0; i < availableDays.size(); i++) {
            System.out.println("\t\t" + (i + 1) + ". " + availableDays.get(i));
        }
        System.out.print("\t\tEnter your preferred day number\t: ");
        int dayChoice = scanner.nextInt();
        scanner.nextLine();
        String day = availableDays.get(dayChoice - 1);

        boolean appointmentsFound = false;

        System.out.println("-----------------------------------------------------");
        System.out.println("\t\tAppointments for " + day + ":");
        for (Appointment appointment : appointments) {
            if (appointment.getDate().equalsIgnoreCase(day)) {
                System.out.println("-----------------------------------------------------");
                printAppointmentDetails(appointment);
                System.out.println("-----------------------------------------------------");
                appointmentsFound = true;
            }
        }

        if (!appointmentsFound) {
            System.out.println("\t\tNo appointments found for " + day + ".");
        }
    }

    @Override
    public void searchAppointment(Scanner scanner) {
        System.out.println("=======================================================");
        System.out.println("\t\tSearch Appointment");
        System.out.println("=======================================================");
        System.out.print("\t\tSearch by :");
        System.out.println("\t1. Patient Name ");
        System.out.println("\t\t\t\t\t2. Appointment ID");
        System.out.println("-------------------------------------------------------");
        System.out.print("\t\tEnter your selection : ");
        int searchOption = scanner.nextInt();
        System.out.println("-------------------------------------------------------");
        scanner.nextLine();

        boolean appointmentFound = false;

        if (searchOption == 1) {
            System.out.print("\t\tEnter Patient Name : ");
            String patientName = scanner.nextLine();

            for (Appointment appointment : appointments) {
                if (appointment.getPatient().getPatientName().equalsIgnoreCase(patientName)) {
                    printAppointmentDetails(appointment);
                    appointmentFound = true;
                }
            }
        } else if (searchOption == 2) {
            System.out.print("\t\tEnter Appointment ID : ");
            int appointmentId = scanner.nextInt();
            scanner.nextLine();

            for (Appointment appointment : appointments) {
                if (appointment.getAppointmentID() == appointmentId) {
                    printAppointmentDetails(appointment);
                    appointmentFound = true;
                    break;
                }
            }
        } else {
            System.out.println("\t\tInvalid option. Please select 1 or 2.");
            return;
        }

        if (!appointmentFound) {
            System.out.println("\t\tNo appointments found.");
        }
    }

    private void printAppointmentDetails(Appointment appointment) {
        System.out.println("\t\tAppointment ID\t: " + appointment.getAppointmentID());
        System.out.println("\t\tPatient NIC\t\t: " + appointment.getPatient().getPatientNIC());
        System.out.println("\t\tPatient Name\t: " + appointment.getPatient().getPatientName());
        System.out.println("\t\tDoctor\t\t\t: " + appointment.getDoctor().getDoctorName());
        System.out.println("\t\tDay\t\t\t\t: " + appointment.getDate());
        System.out.println("\t\tTime\t\t\t: " + appointment.getTimeSlot());
        System.out.println("\t\tTreatment\t\t: " + appointment.getTreatment());
    }

    @Override
    public void updateAppointment(Scanner scanner) {

        System.out.println("=======================================================");
        System.out.println("\t\tUpdate Appointment");
        System.out.println("=======================================================");
        System.out.print("\t\tEnter Appointment ID to update : ");
        int appointmentId = scanner.nextInt();
        scanner.nextLine();

        Appointment appointmentToUpdate = null;

        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID() == appointmentId) {
                appointmentToUpdate = appointment;
                break;
            }
        }

        if (appointmentToUpdate == null) {
            System.out.println("\t\tAppointment not found with ID : " + appointmentId);
            return;
        }

        System.out.println("-------------------------------------------------------");
        System.out.println("\t\tAppointment found =>");
        printAppointmentDetails(appointmentToUpdate);
        System.out.println("-------------------------------------------------------");

        System.out.println("\t\tWhat would you like to update?");
        System.out.println("\t\t1. Day and Time Slot");
        System.out.println("\t\t2. Treatment");
        System.out.print("\t\tEnter your selection : ");
        int updateChoice = scanner.nextInt();
        scanner.nextLine();

        switch (updateChoice) {
            case 1:
                updateDayAndTime(scanner, appointmentToUpdate);
                break;
            case 2:
                updateTreatment(scanner, appointmentToUpdate);
                break;
            default:
                System.out.println("\t\tInvalid choice.");
                break;
        }

        System.out.println("\t\tAppointment updated successfully!");
    }

    private void updateDayAndTime(Scanner scanner, Appointment appointment) {
        Doctor doctor = appointment.getDoctor();

        doctor.releaseTime(appointment.getDate(), appointment.getTimeSlot());

        List<String> availableDays = doctor.getAvailableDays();
        System.out.println("-------------------------------------------------------");
        System.out.println("\t\tAvailable Days");
        System.out.println("-------------------------------------------------------");
        for (int i = 0; i < availableDays.size(); i++) {
            System.out.println("\t\t" + (i + 1) + ". " + availableDays.get(i));
        }
        System.out.print("\t\tEnter your preferred day number\t: ");
        int dayChoice = scanner.nextInt();
        scanner.nextLine();
        String newDay = availableDays.get(dayChoice - 1);

        List<String> availableSlots = doctor.getAvailableSlots(newDay);
        System.out.println("-------------------------------------------------------");
        System.out.println("\t\tAvailable Time Slots");
        System.out.println("-------------------------------------------------------");
        for (int i = 0; i < availableSlots.size(); i++) {
            System.out.println("\t\t" + (i + 1) + ". " + availableSlots.get(i));
        }
        System.out.print("\t\tEnter preferred time slot number\t: ");
        int slotChoice = scanner.nextInt();
        scanner.nextLine();
        String newTimeSlot = availableSlots.get(slotChoice - 1);

        doctor.bookedTime(newDay, newTimeSlot);

        appointment.setDate(newDay);
        appointment.setTimeSlot(newTimeSlot);
    }

    private void updateTreatment(Scanner scanner, Appointment appointment) {
        viewTreatments();
        System.out.print("\t\tEnter new Treatment Number : ");
        int treatmentChoice = scanner.nextInt();
        scanner.nextLine();

        if (treatmentChoice < 1 || treatmentChoice > treatments.size()) {
            System.out.println("\t\tInvalid treatment choice. Update failed.");
            return;
        }

        Treatment newTreatment = treatments.get(treatmentChoice - 1);
        double basePrice = newTreatment.getPrice();
        double newTotalFee = basePrice + basePrice * (2.5 / 100);

        appointment.setTreatment(newTreatment);
        appointment.setTotalFee(newTotalFee);
    }

    @Override
    public void generateInvoice(Scanner scanner) {

        System.out.println("=======================================================");
        System.out.println("\t\tGenerate Invoice");
        System.out.println("=======================================================");
        System.out.print("\t\tEnter Appointment ID to generate invoice : ");
        int appointmentId = scanner.nextInt();
        scanner.nextLine();

        Appointment appointmentToGenerateInvoice = null;

        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID() == appointmentId) {
                appointmentToGenerateInvoice = appointment;
                break;
            }
        }

        if (appointmentToGenerateInvoice == null) {
            System.out.println("\t\tAppointment not found with ID : " + appointmentId);
            return;
        }

        printGeneratedInvoice(appointmentToGenerateInvoice);

    }

    private void printGeneratedInvoice(Appointment appointment) {
        String patientInfo = "\t\tPatient Name\t\t: " + appointment.getPatient().getPatientName() +
                "\n\t\tPatient NIC\t\t\t: " + appointment.getPatient().getPatientNIC() +
                "\n\t\tEmail\t\t\t\t: " + appointment.getPatient().getPatientEmail() +
                "\n\t\tPhone\t\t\t\t: " + appointment.getPatient().getPatientTelephone();

        String doctorInfo = "\t\tDoctor\t\t\t\t: " + appointment.getDoctor().getDoctorName();
        String appointmentDetails = "\t\tDate\t\t\t\t: " + appointment.getDate() +
                "\n\t\tTime\t\t\t\t: " + appointment.getTimeSlot();

        String treatmentInfo = "\t\tTreatment\t\t\t: " + appointment.getTreatment() +
                "\n\t\tBase Price\t\t\t: LKR " + appointment.getTotalFee() +
                "\n\t\tRegistration Fee\t: LKR " + 500.00;

        double taxAmount = Math.round(appointment.getTotalFee() * 2.5) / 100.0;
        double totalAmount = Math.round((appointment.getTotalFee() + taxAmount + 500) * 100.0) / 100.0;

        String feeDetails = "\t\tTax (2.5%)\t\t\t: LKR " + taxAmount +
                "\n\t\tTotal Fee\t\t\t: LKR " + totalAmount;

        String invoice = "\n-------------------------------------------------------\n\t\t\t\t\tInvoice" +
                "\n-------------------------------------------------------\n" + patientInfo + "\n\n" + doctorInfo + "\n\n" +
                appointmentDetails + "\n\n" + treatmentInfo + "\n\n" + feeDetails +
                "\n-------------------------------------------------------";

        System.out.println(invoice);
    }
}
