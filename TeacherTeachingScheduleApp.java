/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teachingschedule;

/**
 *
 * @author Pheakkorny
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeacherTeachingScheduleApp {
    public static void main(String[] args) {
        List<TeachingSession> sessions = new ArrayList<>();
        TeachingScheduleManager scheduleManager = new TeachingScheduleManager(sessions);

        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("Teacher Teaching Schedule System");
            System.out.println("1. Add new Session");
            System.out.println("2. Display Schedule");
            System.out.println("3. Update Session");
            System.out.println("4. Delete Session");
            System.out.print("5. Exit ");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    scheduleManager.addNewSession(scanner); // Pass the scanner object
                    break;
                case 2:
                    scheduleManager.displaySessions();
                    break;
                case 3:
                    scheduleManager.updateSession(scanner); // Pass the scanner object
                    break;
                case 4:
                    scheduleManager.deleteSession(scanner);
                    break;
                case 5:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
