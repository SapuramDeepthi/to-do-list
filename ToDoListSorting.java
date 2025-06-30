package com.projects;

import java.util.*;

class Task {
    String title;
    String priority; 
    String dueDate;  

    public Task(String title, String priority, String dueDate) {
        this.title = title;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public int getPriorityValue() {
        return switch (priority.toLowerCase()) {
            case "very important" -> 1;
            case "important" -> 2;
            case "normal" -> 3;
            default -> 4;
        };
    }

    @Override
    public String toString() {
        return "Task Title   : " + title + "\n" +
               "Priority     : " + priority + "\n" +
               "Due Date     : " + dueDate + "\n";
    }
}

public class ToDoListSorting {
    static Scanner sc = new Scanner(System.in);
    static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- TO-DO LIST ---");
            System.out.println("1. Add Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Sort by Priority");
            System.out.println("4. Sort by Due Date");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> addTask();
                case "2" -> viewTasks();
                case "3" -> sortByPriority();
                case "4" -> sortByDueDate();
                case "5" -> {
                    System.out.println("Exiting To-Do App. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    static void addTask() {
        System.out.print("Enter Task Title: ");
        String title = sc.nextLine();

        String priority;
        while (true) {
            System.out.print("Enter Priority (Normal / Important / Very Important): ");
            priority = sc.nextLine();
            if (priority.equalsIgnoreCase("normal") ||
                priority.equalsIgnoreCase("important") ||
                priority.equalsIgnoreCase("very important")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid priority.");
            }
        }

        System.out.print("Enter Due Date (yyyy-mm-dd): ");
        String dueDate = sc.nextLine();

        tasks.add(new Task(title, priority, dueDate));
        System.out.println("Task added successfully!");
    }

    static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("\n--- Task List ---");
            for (Task t : tasks) {
                System.out.println(t);
            }
        }
    }

    static void sortByPriority() {
        tasks.sort(Comparator.comparingInt(Task::getPriorityValue));
        System.out.println("Tasks sorted by priority (Very Important → Normal).");
        viewTasks();
    }

    static void sortByDueDate() {
        tasks.sort(Comparator.comparing(t -> t.dueDate));
        System.out.println("Tasks sorted by due date (earliest first).");
        viewTasks();
    }
}
