import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDao {

    public static void addTask(Task task) {
        String sql ="INSERT INTO tasks(title,description,status) VALUES (?,?,?)";

        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, task.getTitle());
            pstmt.setString(2, task.getDescription());
            pstmt.setString(3, task.getStatus());
            pstmt.executeUpdate();
            System.out.println("Görev eklendi!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
public static List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";
        try (Connection conn = DatabaseHelper.connect();
        Statement stmt=conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                Task task = new Task(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("status")
                        );
                tasks.add(task);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
}
public static void deleteTask(int id) {
        String sql ="DELETE FROM tasks where id =?";

        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
            System.out.println("Görev silindi");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
}
public static void updateStatus (int id, String status)
{
    String sql = "UPDATE TASKS SET STATUS=? WHERE ID= ?";
    try (Connection conn = DatabaseHelper.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, status);
        pstmt.setInt(2,id);
        int affectedRows= pstmt.executeUpdate();
        if (affectedRows>=1)
        {
            System.out.println("Görev durumu başarıyla güncellendi");
        } else {
            System.out.println("Güncellenecek görev bulunamadı.");
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}
public static void updateTask (Task task){
        String sql = "UPDATE tasks SET title =?, description=?, status=? where id=?";
        try (Connection conn = DatabaseHelper.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, task.getTitle());
            pstmt.setString(2,task.getDescription());
            pstmt.setString(3,task.getStatus());
            pstmt.setInt(4,task.getId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows>=1)
            {
                System.out.println("Görev başarıyla güncellendi");
            } else {
                System.out.println("Güncellenecek görev bulunamadı.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
}
public static Task getTaskById(int id) {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Task(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("status")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static List <Task> getTasksByStatus (String status){
        List <Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE status =?";

        try (Connection conn = DatabaseHelper.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,status);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("status")
                );
                tasks.add(task);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }
    public static List<Task> searchTasksByTitle (String keyword){
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE title LIKE ?";
        try (Connection conn = DatabaseHelper.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,"%"+keyword+"%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("status")
                );
                tasks.add(task);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return tasks;
    }
    public static void saveTaskstoFile (String filename){
        List <Task> tasks = getAllTasks();
        try ( FileWriter writer = new FileWriter(filename)){
            for (Task task : tasks){
                writer.write(task.getId() +"," + task.getTitle()+"," + task.getDescription() + "," + task.getStatus() + "\n");
            }
            System.out.println("Dosya yolu: " + new File(filename).getAbsolutePath());
            System.out.println("Görevler dosyaya kaydedildi");
        }catch (IOException e) {
            System.out.println("Dosyaya yazılamadı:" + e.getMessage());
        }
    }
    public static void loadTasksFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader (filename))) {
            String line;
            while ((line = reader.readLine())!= null){
                String[] parts = line.split(",");
                if (parts.length ==4){
                    Task task = new Task (
                            Integer.parseInt(parts[0]),
                            parts[1],
                            parts[2],
                            parts[3]
                    );
                    addTask(task);
                }
            }
            System.out.println("Görevler dosyadan yüklendi!");
        } catch (IOException e){
            System.out.println("Dosya okunamadı "+ e.getMessage());
        }
    }
}




