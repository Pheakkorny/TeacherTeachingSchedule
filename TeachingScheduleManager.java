/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teachingschedule;

/**
 *
 * @author Pheakkorny
 */
import java.util.List;
import java.util.Scanner;
import java.util.*;

public class TeachingScheduleManager {
    private List<TeachingSession> sessions;

    public TeachingScheduleManager(List<TeachingSession> sessions) {
        this.sessions = sessions;
    }
    public void addNewSession(Scanner scanner) {
        System.out.print("Enter day: ");
        String day = scanner.next();
        // Add validation for day
        if (!isValidDay(day)) {
            System.out.println("Invalid day entered.");
            return;
        }
        
        System.out.print("Enter time (HH:mm): ");
        String time = scanner.next();
        // Add validation for time format
        if (!isValidTimeFormat(time)) {
            System.out.println("Invalid time format. Time must be in HH:mm format.");
            return;
        }
        
        int sessionNumber;
        while (true) {
            System.out.print("Enter session: ");
            try {
                sessionNumber = scanner.nextInt();
                if (sessionNumber <= 0 || sessionNumber > 6) {
                    System.out.println("Session number must be between 1 and 6.");
                    continue;
                }
                if (isDuplicateSessionNumber(day, sessionNumber)) {
                    System.out.println("Session number already exists for this day. Please enter a different session number.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid session number. Please enter a positive integer.");
                scanner.next(); // Consume invalid input
            }
        }
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter subject: ");
        String subject = scanner.nextLine();
        // Add validation for subject
        if (subject.isEmpty()) {
            System.out.println("Subject cannot be empty.");
            return;
        }
        
        System.out.print("Enter teacher: ");
        String teacher = scanner.nextLine();
        // Add validation for teacher
        if (teacher.isEmpty()) {
            System.out.println("Teacher name cannot be empty.");
            return;
        }

        // Check if there are already 6 sessions for the specified day
        int sessionsForDay = countSessionsForDay(day);
        // Check if the session number is less than or equal to 6
        if (sessionsForDay >= 6) {
            System.out.println("Maximum sessions reached for the day. Cannot add more.");
            return;
        }

        // Add the new session
        TeachingSession newSession = new TeachingSession(day, time, sessionNumber, subject, teacher);
        sessions.add(newSession);
        System.out.println("Session added successfully.");
    }
    public void displaySessions() {
        if (sessions.isEmpty()) {
            System.out.println("No sessions available.");
            return;
        }

        System.out.println("Teacher Teaching Schedule:");
        System.out.printf("%-10s %-15s %-8s %-15s %-15s%n", "Day", "Time", "Session", "Subject", "Teacher");
        for (TeachingSession session : sessions) {
            System.out.printf("%-10s %-15s %-8d %-15s %-15s%n",
                    session.getDay(), session.getTime(), session.getSession(),
                    session.getSubject(), session.getTeacher());
        }
    }

    public void updateSession(Scanner scanner) {
        if (sessions.isEmpty()) {
            System.out.println("No sessions available for update.");
            return;
        }

        boolean sessionFound = false;
        while (!sessionFound) {
            scanner.nextLine(); // Consume newline
            System.out.print("Enter the day of the session to update: ");
            String updateDay = scanner.nextLine();
            System.out.print("Enter the session number to update within " + updateDay + ": ");
            int sessionNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            for (TeachingSession session : sessions) {
                if (session.getDay().equalsIgnoreCase(updateDay) && session.getSession() == sessionNumber) {
                    System.out.print("Enter new day: ");
                    String newDay = scanner.next();
                    System.out.print("Enter new time: ");
                    String newTime = scanner.next();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new subject: ");
                    String newSubject = scanner.nextLine();
                    System.out.print("Enter new teacher: ");
                    String newTeacher = scanner.nextLine();

                    // Update session properties
                    session.setDay(newDay);
                    session.setTime(newTime);
                    session.setSubject(newSubject);
                    session.setTeacher(newTeacher);

                    System.out.println("Session updated successfully.");
                    sessionFound = true;
                    break;
                }
            }

            if (!sessionFound) {
                System.out.println("Session not found for the specified day and session number.");
                System.out.println("Do you want to try again? (yes/no): ");
                String tryAgain = scanner.nextLine();
                if (!tryAgain.equalsIgnoreCase("yes")) {
                    break;
                }
            }
        }
    }
    // Method to check if a session number is duplicate for a given day
    private boolean isDuplicateSessionNumber(String day, int sessionNumber) {
    for (TeachingSession session : sessions) {
        if (session.getDay().equals(day) && session.getSession() == sessionNumber) {
            return true;
        }
    }
    return false;
}

    // Example method to count sessions for a given day
    private int countSessionsForDay(String day) {
        int count = 0;
        for (TeachingSession session : sessions) {
            if (session.getDay().equals(day)) {
                count++;
            }
        }
        return count;
    }

    // Example method to validate time format
    private boolean isValidTimeFormat(String time) {
        // Implement your time format validation logic here
        // For example, you could use regular expressions to check if the time matches a specific format
        return time.matches("\\d{2}:\\d{2}");
    }

    // Example method to validate day
    private boolean isValidDay(String day) {
        // Implement your logic to validate the day here
        // For example, you could check if the day is one of the valid days of the week
        Set<String> validDays = new HashSet<>(Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));
        return validDays.contains(day);
    }
    public void deleteSession(Scanner scanner) {
        if (sessions.isEmpty()) {
            System.out.println("No sessions available for deletion.");
            return;
        }
        scanner.nextLine(); // Consume newline
        System.out.print("Enter the day of the session to delete: ");
        String deleteDay = scanner.nextLine();
        System.out.print("Enter the session number to delete within " + deleteDay + ": ");
        int sessionNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        boolean sessionFound = false;
        Iterator<TeachingSession> iterator = sessions.iterator();
        while (iterator.hasNext()) {
            TeachingSession session = iterator.next();
            if (session.getDay().equalsIgnoreCase(deleteDay) && session.getSession() == sessionNumber) {
                iterator.remove();
                System.out.println("Session deleted successfully.");
                sessionFound = true;
                break;
            }
        }

        if (!sessionFound) {
            System.out.println("Session not found for the specified day and session number.");
        }
    }
}
