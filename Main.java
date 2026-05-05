/**
 * Main.java
 * Provides a console-based menu for:
 *Adding assignments
 *Removing assignments
 *Marking assignments complete
 *Viewing sorted assignments
 * Author: Landon Whitlow
 */

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        System.out.println("=== Welcome to the Digital Study Planner ===");

        while (running) {

            System.out.println();
            System.out.println("--- MENU ---");
            System.out.println("1. Add Assignment");
            System.out.println("2. Remove Assignment");
            System.out.println("3. Mark Complete");
            System.out.println("4. View All (Sorted by Due Date)");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                // Add a new assignment
                System.out.print("Title: ");
                String title = scanner.nextLine();

                System.out.print("Course: ");
                String course = scanner.nextLine();

                System.out.print("Due Date (MM/DD/YYYY): ");
                String dueDate = scanner.nextLine();

                manager.addTask(title, course, dueDate);

            } else if (choice.equals("2")) {
                // Remove an assignment
                System.out.print("Title to remove: ");
                String title = scanner.nextLine();
                manager.removeTask(title);

            } else if (choice.equals("3")) {

                System.out.print("Title to mark complete: ");
                String title = scanner.nextLine();
                manager.markComplete(title);

            } else if (choice.equals("4")) {

                Assignment[] sorted = manager.getSortedTasks();

                if (sorted.length == 0) {
                    System.out.println("(no assignments yet)");
                } else {
                    System.out.println();
                    System.out.println("--- Assignments (earliest due first = highest priority) ---");
                    for (int i = 0; i < sorted.length; i++) {
                        System.out.println((i + 1) + ". " + sorted[i].toString());
                    }
                }

            } else if (choice.equals("5")) {

                running = false;
                System.out.println("Goodbye! Happy studying!");

            } else {
                System.out.println("Invalid choice. Please pick 1-5.");
            }
        }

        scanner.close();
    }
}
