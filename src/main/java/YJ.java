import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class YJ {
    public static void main(String[] args) {

        System.out.println("Hello! I'm YJ. What can I do for you?");

        // ArrayList of String
        List<Task> tasks = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    System.out.println((i + 1) + "." + task.toString());
                }

            } else if (input.startsWith("delete")) {
                try {
                    Integer taskNumber = Integer.parseInt(input.split(" ")[1]);
                    if (taskNumber != null && tasks.get(taskNumber - 1) != null) {
                        Task task = tasks.remove(taskNumber - 1);
                        System.out.println("I've removed this task as u lazily requested:");
                        System.out.println(task.toString());
                        System.out.println("Now you have likee this many tasks left: " + tasks.size());
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Crapadoodle! You need to specify a task number or a valid task to delete.");
                }
            } else if (input.startsWith("mark")) {
                Integer taskNumber = Integer.parseInt(input.split(" ")[1]);
                if (taskNumber != null && tasks.get(taskNumber - 1) != null) {
                    Task task = tasks.get(taskNumber - 1);
                    task.markAsDone();
                    System.out.println("Niceoooo you're done wit this: ");
                    System.out.println(task.toString());
                }
            } else if (input.startsWith("unmark")) {
                Integer taskNumber = Integer.parseInt(input.split(" ")[1]);
                if (taskNumber != null && tasks.get(taskNumber - 1) != null) {
                    Task task = tasks.get(taskNumber - 1);
                    task.markAsNotDone();
                    System.out.println("Ok lah you haven't finish this yet");
                    System.out.println(task.toString());
                }
            } else if (input.startsWith("todo")) {
                try {
                    ToDo newToDo = new ToDo(input.replaceAll("todo ", "").trim());
                    tasks.add(newToDo);
                    System.out.println("Ok! I've added this todo! " + newToDo.toString());
                    System.out.println("You now have this many tasks: " + tasks.size());
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("deadline")) {
                try {
                    String by = input.split("/by")[1].trim();
                    Deadline newDeadline = new Deadline(input.split("/by")[0].trim(), by);
                    tasks.add(newDeadline);
                    System.out.println("Ok! I've added this deadline!" + newDeadline.toString());
                    System.out.println("You now have this many tasks: " + tasks.size());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Crapadoodle! You need to specify a deadline in the correct format!");
                }
            } else if (input.startsWith("event")) {
                try {
                    // Given this string: "event project meeting /from Mon 2pm /to 4pm" extract the string after /from and after /to
                    String from = input.split("/from")[1].split("/to")[0].trim();
                    String to = input.split("/to")[1].trim();
                    Event newEvent = new Event(input.split("/from")[0].trim(), from, to);
                    tasks.add(newEvent);
                    System.out.println("Ok! I've added this event!" + newEvent.toString());
                    System.out.println("You now have this many tasks: " + tasks.size());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Crapadoodle! You need to specify an event in the correct format!");
                }
            } else {
                System.out.println("Crapdoodledy, I don't know what that means man");
            }

            input = sc.nextLine();
        }

        System.out.println("Byebye, YJ will miss you :(");
        sc.close();
    }
}

