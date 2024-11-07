/**
 * @author K2460782
 */
import service.AppointmentService;
import service.impl.AppointmentServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AppointmentService appointmentService = new AppointmentServiceImpl();
        while (true) {
            System.out.println("=======================================================");
            System.out.println("\t\t\t\t\tMain Menu");
            System.out.println("=======================================================");
            System.out.println("\t\t1. Make Appointment");
            System.out.println("\t\t2. View Appointments by Date");
            System.out.println("\t\t3. Search Appointment");
            System.out.println("\t\t4. Update Appointment");
            System.out.println("\t\t5. Generate Invoice");
            System.out.println("\t\t6. Exit");
            System.out.println("-------------------------------------------------------");
            System.out.print("\t\tEnter Your Selection : ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    appointmentService.makeAppointment(scanner);
                    break;
                case 2:
                    appointmentService.viewAppointmentsByDate(scanner);
                    break;
                case 3:
                    appointmentService.searchAppointment(scanner);
                    break;
                case 4:
                    appointmentService.updateAppointment(scanner);
                    break;
                case 5:
                    appointmentService.generateInvoice(scanner);
                    break;
                case 6:
                    System.out.println("Exiting system.");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}