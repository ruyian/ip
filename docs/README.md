# Duke User Guide

Duke is a Command-Line Interface application. It is used for recording and tracking tasks.

- [Duke User Guide](#duke-user-guide)
  - [Before we start](#before-we-start)
  - [Features](#features)
    - [Adding todo : `todo`](#adding-todo--todo)
    - [Add event : `event`](#add-event--event)
    - [Add deadline : `deadline`](#add-deadline--deadline)
    - [List all tasks : `list`](#list-all-tasks--list)
    - [Mark the task as done : `done`](#mark-the-task-as-done--done)
    - [Delete a task : `delete`](#delete-a-task--delete)
    - [Find task : `find`](#find-task--find)
    - [Exit Program : `bye`](#exit-program--bye)
  - [Note about storage](#note-about-storage)
  - [Table of commands](#table-of-commands)
  - [Reference](#reference)

## Before we start

1. Install Java version `11` on your computer
2. Find the lastest Duke.jar by clicking [me](https://github.com/ruyian/ip/releases).
3. Copy Duke.jar to the target directory as the home folder
4. Open terminal and cd to the home folder
5. Run the program using `java -jar Duke.jar` command.
6. We are all done. Type your first command in terminal to talk to Duke.

## Features 

### Adding todo : `todo`

Adds a new todo task to the list of tasks.
Todos are tasks without any date/time attached to it.

Format:

`todo <task name>`

Example of Usage:

`todo visit new theme park`

Expected Outcome:
    
    Got it. I've added this task:
      [T][ ] visit new theme park
    Now you have 1 tasks in the list.

### Add event : `event`

Adds a new event task with date/time to task list.
Events are tasks that start at a specific time and ends at a specific time. 

Format:

`event <task name> /at <event date/time>`

Example of Usage:

`event team project meeting /at Friday Afternoon`

Expected Outcome:

    Got it. I've added this task:
      [E][ ] team project meeting (at Friday Afternoon)
    Now you have 1 tasks in the list.

### Add deadline : `deadline`

Adds a new deadline task with deadline to task list.
Deadlines are tasks that need to be done before a specific date/time.

Format:

`deadline <task name> /by <deadline>`

Example of Usage:

`deadline submit report /by: 11/10/2019 5pm`

Expected Outcome:

    Got it. I've added this task:
      [D][ ] submit report (by: 11/10/2019 5pm)
    Now you have 1 tasks in the list.

### List all tasks : `list`

Display all the tasks in the list of tasks.

Format:

`list`

Example of Usage:

`list`

Expected Outcome:

    Here are the tasks in your list:
    1. [T][ ] Exercise
    2. [D][X] CS5222 Assignment (by: 31 Sept 2021)
    3. [E][ ] Watch a movie (at: 1 Oct 2021, 9PM)
    4. [D][ ] Scholarship Application (by: 2 Oct 2021 11:59pm)

Notes: Notice the `[ ]` and `[X]` in the above example? It indicates whether a task is done or not. A task with `[ ]` is to be done and a task with `[X]` has already been done.

### Mark the task as done : `done`

Mark the specified task as done by its index.

Format:

`done <index number>`

Example of Usage:

`done 3`

Expected Outcome:
    
    Nice! I've marked this task as done:
      [E][X] Watch a movie (at: 1 Oct 2021, 9PM)

See, the `[ ]` has been changed to `[X]` after `done 3` command.

Note: You can view the index of a task by typing `list`.


### Delete a task : `delete`

Delete the specified task by its index.

Format:

`delete <index number>`

Example of Usage:

`delete 1`

Expected Outcome:

    Noted. I've removed this task:
      [T][ ] Exercise
    Now you have 2 tasks in the list.

Note: You can view the index of a task by typing `list`.

### Find task : `find`

Find all tasks in the list that contain the given keyword.

Format:

`find <keyword>`

Example of Usage:

`find coding`

Expected Outcome:

    Here are the matching tasks in your list:
    1. [D][ ] CS2030 coding Assignment (by: 31 Sept 2021)
    2. [T][X] Leetcode coding practise

### Exit Program : `bye`

Exits the Duke program.

Format:

`bye`

Example of Usage:

`bye`

Expected Outcome:

    Bye. Hope to see you again soon!


## Note about storage

All data are stored under a text file under directory of "./data/duke.txt" to the home folder. Text file is modified and all changes are automatically saved whenever there is a change to the list e.g. deleting, mark as done, adding new todo/event/deadline. All data are saved locally and no data will be collected from you.

Every time you restart Duke, Duke will automatically reload the all the tasks you have previously keyyed in from the text file.

## Table of commands

Command | Format
--------|--------
todo    |`todo <task name>`
event   |`event <task name> /at <event date/time>`
deadline|`deadline <task name> /by <deadline>`
list    |`list`
done    |`done <index number>`
delete  |`delete <index number>`
find    |`find <keyword>`
bye     |`bye`

## Reference
The template of the User Guide is followed by [AddressBook Level 3 (AB3) User Guide](https://se-education.org/addressbook-level3/UserGuide.html#editing-a-person--edit).