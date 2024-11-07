package service;

import java.util.Scanner;

public interface AppointmentService {
    void makeAppointment(Scanner scanner);

    void viewAppointmentsByDate(Scanner scanner);

    void searchAppointment(Scanner scanner);

    void updateAppointment(Scanner scanner);

    void generateInvoice(Scanner scanner);
}
