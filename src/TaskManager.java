import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages an array of tasks
 */
public class TaskManager
{
    private ArrayList<Task> tasks;

    /**
     * Initializes the tasks ArrayList to an empty ArrayList
     */
    public TaskManager()
    {
        tasks = new ArrayList<>();
    }


    /**
     * Sees if a task is already in the list
     * @param taskName the task we are seeing if it is in the list
     * @return true if taskName is not in the list, false if it is in the list
     */
    public boolean taskNotInList(String taskName)
    {
        for (int index = 0; index < this.tasks.size(); index++)
        {
            if (this.tasks.get(index).getTaskName().equals(taskName))
            {
                return false;
            }
        }

        return true;
    }


    /**
     * Gives the index of the task we are looking for
     * @param taskName the task we want the index for
     * @return the index of the task
     */
    private int index(String taskName)
    {
        for (int index = 0; index < this.tasks.size(); index++)
        {
            if (this.tasks.get(index).getTaskName().equals(taskName))
            {
                return index;
            }
        }

        return -1;
    }


    /**
     * Adds a task to the list
     * @param taskName the task name we want to add
     * @param dueDateString the due date of the task we want to add
     * @throws Exception if an invalid task is attempted to be added
     */
    public void addTask(String taskName, String dueDateString) throws Exception
    {
        Task task = new Task(taskName, dueDateString);
        tasks.add(task);
    }


    /**
     * Prints the tasks out in list format
     */
    public void printTasks()
    {
        for (int index = 0; index < this.tasks.size(); index++)
        {
            System.out.println((index + 1) + ". " + tasks.get(index));
        }
    }


    /**
     * Removes a task from the list
     * @param index the index of the task we want to remove
     */
    public void deleteTask(int index)
    {
        this.tasks.remove(index);
    }


    /**
     * Saves the tasks to a file
     * @param fileName the file we want to save to
     * @throws FileNotFoundException if the file cannot be found
     */
    public void saveTasks(String fileName) throws FileNotFoundException
    {
        File file = new File(fileName);
        PrintWriter filePrintWriter = new PrintWriter(file);

        for (int index = 0; index < this.tasks.size(); index++)
        {
            filePrintWriter.println(this.tasks.get(index));
        }

        filePrintWriter.close();
    }


    /**
     * Loads tasks from a file and puts them in the array list
     * @param fileName the file we want to load from
     * @throws FileNotFoundException if the file is not found
     */
    public void loadTasks(String fileName) throws FileNotFoundException
    {
        File file = new File(fileName);

        try
        {
           file.createNewFile();
        } catch (Exception e)
        {
            System.out.println("An error occurred opening the file");
        }

        Scanner fileScanner = new Scanner(file);


        while (fileScanner.hasNextLine())
        {
            String line = fileScanner.nextLine();



            String taskName = "";
            String dateString = "";

            int index = 0;
            while (line.charAt(index) != ':')
            {
                taskName += line.charAt(index);
                index++;
            }
            index++;

            while (index < line.length())
            {
                dateString += line.charAt(index);
                index++;
            }

            dateString = dateString.replace('/', ' ');
            Scanner dateScanner = new Scanner(dateString);

            try
            {
                this.addTask(taskName, dateString);
            } catch(Exception e)
            {
                break;
            }
        }

    }


    /**
     * Gives the amount of tasks in our list
     * @return the length of this.tasks
     */
    public int totalTasks()
    {
        return this.tasks.size();
    }


    /**
     * Sorts the tasks by due date with the earliest ones early in the ArrayList
     */
    public void sortTasks()
    {
        for (int currentIndex = 0; currentIndex < this.totalTasks(); currentIndex++)
        {
            int otherIndex = currentIndex;

            while (otherIndex > 0 && this.tasks.get(otherIndex).dueBefore(this.tasks.get(otherIndex - 1)))
            {
                Task temp = this.tasks.get(otherIndex);
                this.tasks.set(otherIndex, this.tasks.get(otherIndex - 1));
                this.tasks.set(otherIndex - 1, temp);

                otherIndex--;
            }
        }
    }
}
