// ─────────────────────────────────────
//  Task.java  — stores one task's data
// ─────────────────────────────────────
public class Task {

    String  title;
    String  priority;    // "HIGH", "MEDIUM", "LOW"
    boolean isCompleted;

    // Constructor
    public Task(String title, String priority) {
        this.title       = title;
        this.priority    = priority;
        this.isCompleted = false;
    }

    // Display a single task
    public void display(int index) {
        String status = isCompleted ? "[DONE]" : "[    ]";
        System.out.println("  " + index + ". " + status + " [" + priority + "] " + title);
    }
}
