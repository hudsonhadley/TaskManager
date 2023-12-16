import java.util.Scanner;

/**
 * A task class that stores the name and due date
 */
public class Task
{
    private String taskName;
    private Date dueDate;


    /**
     * Initializes the task
     * @param taskName the task name we want to initialize
     * @param dateString the due date of the task
     * @throws Exception if an invalid date is entered
     */
    public Task(String taskName, String dateString) throws Exception
    {
        setTaskName(taskName);
        setDueDate(dateString);
    }


    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
    }


    public void setDueDate(String dueDateString) throws Exception
    {
        dueDateString = dueDateString.replace('/', ' ');
        // Dates must be entered as MM/DD/YYYY
        Scanner dueDateStringScanner = new Scanner(dueDateString);

        int day;
        int month;
        int year;

        // Getting day month year from the string
        if (!dueDateStringScanner.hasNextInt())
        {
            throw new Exception("Date Error: Day not found. Dates must be entered as MM/DD/YYYY");
        }
        month = dueDateStringScanner.nextInt();

        if (!dueDateStringScanner.hasNextInt())
        {
            throw new Exception("Date Error: Month not found. Dates must be entered as MM/DD/YYYY");
        }
        day = dueDateStringScanner.nextInt();

        if (!dueDateStringScanner.hasNextInt())
        {
            throw new Exception("Date Error: Year not found. Dates must be entered as MM/DD/YYYY");
        }
        year = dueDateStringScanner.nextInt();


        this.dueDate = new Date(day, month, year);
    }

    public Date getDueDate()
    {
        return dueDate;
    }

    public String getTaskName()
    {
        return taskName;
    }


    public String toString()
    {
        return this.taskName + ": " + this.dueDate;
    }


    /**
     * Says if the current task is due before the other task
     * @param other the task we want to compare to
     * @return if task.date is before other.date
     */
    public boolean dueBefore(Task other)
    {
        return this.dueDate.lessThan(other.dueDate);
    }
}
