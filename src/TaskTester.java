public class TaskTester
{
    public static void main(String[] args)
    {
        try
        {
            Task task = new Task("Study", "12/4/2025");
            System.out.println(task);
        } catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
