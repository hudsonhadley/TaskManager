# TaskManager
This project is a very simply task manager. This was originally
created to study for a Computer Programming I final exam. It includes
various topics and issues which were covered in the course, most
notably file I/O and exceptions. This program is designed to interact
with a user who will try to destroy the program from doing what it is
meant to do. Some cracks may exist which I am not aware about, but it
has been rigorously tested by myself.

The src folder contains three base classes:
* [Date](src/Date.java) - defines what a date is in the MM/DD/YYYY
format
* [Task](src/Task.java) - defines what a task is including a name
and due date
* [TaskManager](src/TaskManager.java) - defines what a task manager
is including a list of tasks

These three base classes combine to run in [Main](src/Main.java)
which creates a [TaskManager]() and gets user input to manipulate
it. The user can input a number 1 through 5 to do different things.
1. View all tasks
2. Add a task
3. Delete a task
4. Save tasks
5. Quit program

The tasks are saved to tasks.txt which is created in 
[TaskManager](src/TaskManager.java) if it does not exist already.