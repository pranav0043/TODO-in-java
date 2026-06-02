// ─────────────────────────────────────────────────
//  TodoApp.java  — Main console Todo application
//  Concepts used: ArrayList, loops, switch, Scanner
// ─────────────────────────────────────────────────

import java.util.ArrayList;
import java.util.Scanner;

public class TodoApp {

    // ArrayList to store all tasks (like a dynamic array)
    static ArrayList<Task> tasks = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════╗");
        System.out.println("║      📝 JAVA TODO APP        ║");
        System.out.println("╚══════════════════════════════╝");

        // Keep running until user chooses to exit
        while (true) {
            showMenu();

            System.out.print("  👉 Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear leftover newline

            switch (choice) {
                case 1 -> addTask();
                case 2 -> showAllTasks();
                case 3 -> markComplete();
                case 4 -> deleteTask();
                case 5 -> showByPriority();
                case 6 -> {
                    System.out.println("\n  👋 Goodbye! Keep being productive!");
                    System.exit(0);
                }
                default -> System.out.println("\n  ❌ Invalid choice! Try again.");
            }
        }
    }

    // ── Show menu ────────────────────────────────
    static void showMenu() {
        System.out.println("\n┌──────────────────────────────┐");
        System.out.println("│           MENU               │");
        System.out.println("├──────────────────────────────┤");
        System.out.println("│  1. ➕ Add Task               │");
        System.out.println("│  2. 📋 View All Tasks         │");
        System.out.println("│  3. ✔  Mark as Complete       │");
        System.out.println("│  4. 🗑  Delete Task            │");
        System.out.println("│  5. 🔍 Filter by Priority     │");
        System.out.println("│  6. 🚪 Exit                   │");
        System.out.println("└──────────────────────────────┘");
    }

    // ── 1. Add a new task ────────────────────────
    static void addTask() {
        System.out.print("\n  Enter task title: ");
        String title = sc.nextLine();

        if (title.isEmpty()) {
            System.out.println("  ❌ Title cannot be empty!");
            return;
        }

        System.out.println("  Select Priority:");
        System.out.println("  1. HIGH   2. MEDIUM   3. LOW");
        System.out.print("  👉 Enter choice: ");
        int p = sc.nextInt();
        sc.nextLine();

        String priority = switch (p) {
            case 1 -> "HIGH";
            case 2 -> "MEDIUM";
            case 3 -> "LOW";
            default -> "MEDIUM";
        };

        tasks.add(new Task(title, priority));
        System.out.println("  ✅ Task added successfully!");
    }

    // ── 2. Show all tasks ────────────────────────
    static void showAllTasks() {
        System.out.println("\n  📋 ALL TASKS (" + tasks.size() + " total)");
        System.out.println("  ─────────────────────────────────────");

        if (tasks.isEmpty()) {
            System.out.println("  No tasks yet! Add some tasks first.");
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).display(i + 1);  // display task with number
        }

        // Show summary at the bottom
        int done    = countCompleted();
        int pending = tasks.size() - done;
        System.out.println("  ─────────────────────────────────────");
        System.out.println("  ✔ Done: " + done + "   ⏳ Pending: " + pending);
    }

    // ── 3. Mark a task as complete ───────────────
    static void markComplete() {
        showAllTasks();

        if (tasks.isEmpty()) return;

        System.out.print("\n  Enter task number to mark complete: ");
        int num = sc.nextInt();
        sc.nextLine();

        // Validate the number
        if (num < 1 || num > tasks.size()) {
            System.out.println("  ❌ Invalid task number!");
            return;
        }

        Task t = tasks.get(num - 1);

        if (t.isCompleted) {
            System.out.println("  ⚠️  Task is already marked as done!");
        } else {
            t.isCompleted = true;
            System.out.println("  ✅ Task marked as complete: " + t.title);
        }
    }

    // ── 4. Delete a task ─────────────────────────
    static void deleteTask() {
        showAllTasks();

        if (tasks.isEmpty()) return;

        System.out.print("\n  Enter task number to delete: ");
        int num = sc.nextInt();
        sc.nextLine();

        if (num < 1 || num > tasks.size()) {
            System.out.println("  ❌ Invalid task number!");
            return;
        }

        String deletedTitle = tasks.get(num - 1).title;
        tasks.remove(num - 1);   // remove from ArrayList
        System.out.println("  🗑️  Deleted: " + deletedTitle);
    }

    // ── 5. Filter by priority ────────────────────
    static void showByPriority() {
        System.out.println("\n  Select Priority to filter:");
        System.out.println("  1. HIGH   2. MEDIUM   3. LOW");
        System.out.print("  👉 Enter choice: ");
        int p = sc.nextInt();
        sc.nextLine();

        String filter = switch (p) {
            case 1 -> "HIGH";
            case 2 -> "MEDIUM";
            case 3 -> "LOW";
            default -> "";
        };

        if (filter.isEmpty()) {
            System.out.println("  ❌ Invalid choice!");
            return;
        }

        System.out.println("\n  🔍 Tasks with priority: " + filter);
        System.out.println("  ─────────────────────────────────────");

        boolean found = false;
        int display = 1;

        // Loop through and only show matching priority tasks
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).priority.equals(filter)) {
                tasks.get(i).display(display++);
                found = true;
            }
        }

        if (!found) {
            System.out.println("  No tasks with " + filter + " priority.");
        }
    }

    // ── Helper: count completed tasks ────────────
    static int countCompleted() {
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).isCompleted) count++;
        }
        return count;
    }
}
