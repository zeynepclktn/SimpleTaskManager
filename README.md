# SimpleTaskManager

A simple console-based task management application developed with Java.

## Features
- ➕ Add new tasks
- 📋 List all tasks
- 🗑️ Delete tasks
- 🔄 Update the status of a task (e.g., active/completed)
- ✏️ Update the title, description, and status of a task
- 💾 Save all tasks to a file (CSV format)

## Technologies Used
- Java 17+
- SQLite (via JDBC connection)
- No external frameworks (pure Java)

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/SimpleTaskManager.git
   cd SimpleTaskManager
   ```
2. Compile and run the project:
   ```bash
   javac *.java
   java Main
   ```

3. Use the console menu to manage your tasks.

## Project Structure
```
/SimpleTaskManager
│
├── DatabaseHelper.java
├── Main.java
├── Task.java
├── TaskDao.java
├── README.md
└── tasks.db (created automatically)
```

## Future Improvements
- Migrate the project to a Spring Boot Web API
- Create RESTful services (GET, POST, PUT, DELETE)
- Implement database operations with JPA/Hibernate
- Add user authentication (login system)
- Add Docker support

## Notes
- The SQLite database file (`tasks.db`) will be created automatically in the project root.
- Exported task data will be saved as a `.csv` file you specify.

