import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main
{
    public static void printInstruction()
    {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("Press 1 to view all tasks");
        System.out.println("Press 2 to add a task");
        System.out.println("Press 3 to delete a task");
        System.out.println("Press 4 to save tasks");
        System.out.println("Press 5 to quit");
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        TaskManager taskManager = new TaskManager();
        Scanner userInputScanner = new Scanner(System.in);

        taskManager.loadTasks("tasks.txt");

        // If there are tasks found in tasks.txt, output them
        if (taskManager.totalTasks() != 0)
        {
            System.out.println("Previous tasks found:");
            taskManager.printTasks();
        } else // No tasks were found
        {
            System.out.println("No previous tasks found");
        }

        int userRequest = 0;

        // Keep going until the user inputs a 5
        while (userRequest != 5)
        {
            // Give the instructions
            printInstruction();

            // First we need to get a valid input
            boolean notValidInput = true;
            while (notValidInput)
            {
                // Get a line from the user
                String inputLine = userInputScanner.nextLine();

                // This will scan the line the user inputted
                Scanner lineScanner = new Scanner(inputLine);

                // If it's not an integer
                if (!lineScanner.hasNextInt())
                {
                    System.out.println("Input must be between 1 and 5");
                } else // It is an integer
                {
                    userRequest = lineScanner.nextInt();

                    // If it's not between 1 and 5
                    if (userRequest < 1 || userRequest > 5)
                    {
                        System.out.println("Input must be between 1 and 5");
                    } else // If it is between 1 and 5
                    {
                        notValidInput = false; // The input is valid if we've gotten through all that
                    }
                }

                lineScanner.close(); // Free up some space
            }

            // If they put in a 1, print the tasks
            if (userRequest == 1)
            {
                System.out.println("---------------------------------------------------------------------------------");
                System.out.println();
                System.out.println("Tasks:");
                taskManager.printTasks();
            } else if (userRequest == 2) // If they put in a 2, ask them for what they want to add
            {
                System.out.println("---------------------------------------------------------------------------------");
                System.out.println();

                String taskName = "";
                String dateString;

                // We need to first get a valid task meaning anything that isn't already in the list
                boolean notValidTask = true;
                while (notValidTask)
                {
                    // Get the name
                    System.out.println("Enter task name: ");
                    taskName = userInputScanner.nextLine();

                    // If the task is not in the list, we can terminate the while loop
                    if (taskManager.taskNotInList(taskName))
                    {
                        notValidTask = false;
                    } else // If the task is in the list, ask for another task
                    {
                        System.out.println("Task already in list");
                    }
                }


                // Now we need a valid date
                boolean notValidDate = true;
                while (notValidDate)
                {
                    // This is simpler now that we have the task name.
                    // We can just try to add the task and if the dateString is not a proper date,
                    // it will catch an exception
                    try
                    {
                        // Get a due date
                        System.out.println("Enter due date: ");
                        dateString = userInputScanner.nextLine();

                        taskManager.addTask(taskName, dateString); // It will crash here if the date is bad
                        taskManager.sortTasks(); // Sort the tasks

                        notValidDate = false;
                    } catch(Exception e)
                    {
                        System.out.println("Invalid date...");
                    }
                }
            } else if (userRequest == 3) // If the user put a 3 in, ask for what they want to delete
            {
                // Show the tasks
                System.out.println("---------------------------------------------------------------------------------");
                System.out.println();
                taskManager.printTasks();

                // We only want to delete something if there is something to delete
                if (taskManager.totalTasks() > 0)
                {
                    int taskIndex;

                    // We need to get a valid number to delete
                    boolean notValidTask = true;
                    while (notValidTask)
                    {
                        System.out.println("Enter task number to delete: ");

                        String lineInput = userInputScanner.nextLine(); // Get the line of input
                        Scanner lineScanner = new Scanner(lineInput); // Parse the input

                        // If the line has an integer next, see if it's a valid index and delete it
                        if (lineScanner.hasNextInt())
                        {
                            taskIndex = lineScanner.nextInt() - 1; // The index will be one less

                            // If it's in the range, delete it and terminate the while loop
                            if (0 <= taskIndex && taskIndex < taskManager.totalTasks())
                            {
                                taskManager.deleteTask(taskIndex);

                                notValidTask = false;
                                lineScanner.close(); // Free up some space
                            } else // If it's not in the range, report to the user
                            {
                                System.out.println("Enter a valid index between 1 and " + taskManager.totalTasks());
                            }
                        } else // If they didn't put it an integer, make it clearer
                        {
                            System.out.println("Enter an integer between 1 and " + taskManager.totalTasks());
                        }
                    }
                } else // If the total tasks are 0, then report to the user
                {
                    System.out.println("No tasks to delete");
                }
            } else if (userRequest == 4) // If the user put in a 4, save the tasks to tasks.txt
            {
                taskManager.saveTasks("tasks.txt");
            }

            // If a five is inputted, all the if statements will be skipped and the while loop will terminate
        }
    }
}
