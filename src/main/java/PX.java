import java.util.Scanner;
import java.util.ArrayList;

public class PX {
    private static String name = "PX";

    private static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    private static void PXSays(String... args) {
        printLine();
        for (String arg : args) {
            System.out.println("    " + arg);
        }
        printLine();
        System.out.println("");
    }

    private static void printList(ArrayList<Task> list) {
        String[] outputs = new String[list.size() + 1];
        outputs[0] = "Here are the tasks in your list:";
        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            outputs[i + 1] = index + ". " + list.get(i).getType() + list.get(i).getStatusIcon() + " " + list.get(i);
        }
        PXSays(outputs);
    }

    private static void addTask(Task t, ArrayList<Task> list) {
        list.add(t);
        PXSays("Got it. I've added this task:", "  " + t.getType() + t.getStatusIcon() + t,
                "Now you have " + list.size() + " tasks in the list.");
    }

    private static void removeTask(Task t, ArrayList<Task> list) {
        list.remove(t);
        PXSays("Noted. I've removed this task:", "  " + t.getType() + t.getStatusIcon() + t,
                "Now you have " + list.size() + " tasks in the list.");
    }

    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        PXSays("Hello! I'm " + name, "What can I do for you?");
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            String cmd = input.split(" ")[0];

            if (cmd.equals("bye")) {
                PXSays("Bye. Hope to see you again soon!");
                sc.close();
                return;
            }

            input = input.substring(cmd.length());

            switch (cmd) {
                case "list":
                    printList(list);
                    break;
                case "mark":
                    Task taskToMark = list.get(Integer.parseInt(input.strip()));
                    taskToMark.toggleIsDone();
                    PXSays(
                            "Nice! I've marked this task as done:",
                            taskToMark.getStatusIcon() + " " + taskToMark);
                    break;
                case "unmark":
                    Task taskToUnmark = list.get(Integer.parseInt(input.strip()));
                    taskToUnmark.toggleIsDone();
                    PXSays("OK, I've marked this task as not done yet:",
                            taskToUnmark.getStatusIcon() + " " + taskToUnmark);
                    break;
                case "todo":
                    try {
                        Task todo = new Todo(input);
                        addTask(todo, list);
                    } catch (TaskException e) {
                        PXSays(e.getMessage());
                    } finally {

                    }
                    break;
                case "deadline":
                    try {
                        Task todo = new Deadline(input);
                        addTask(todo, list);
                    } catch (TaskException e) {
                        PXSays(e.getMessage());
                    } finally {

                    }
                    break;
                case "event":
                    try {
                        Task todo = new Event(input);
                        addTask(todo, list);
                    } catch (TaskException e) {
                        PXSays(e.getMessage());
                    } finally {

                    }
                    break;
                case "delete":
                    Task taskToDelete = list.get(Integer.parseInt(input.strip()) - 1);
                    removeTask(taskToDelete, list);
                    break;
                default:
                    PXSays("OH NO!!! I don't understand this! Try Again!");
            }
        }
    }
}
