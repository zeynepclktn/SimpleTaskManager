import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseHelper {
    private static final String DB_URL="jdbc:sqlite:todo_app.db";
    public static Connection connect() {
        Connection conn=null;
        try {
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Veritabanına başarılı bağlantı sağlandı");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void createTasksTable(){
        String sql= "CREATE TABLE IF NOT EXISTS tasks (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT NOT NULL," +
                "description TEXT," +
                "status TEXT NOT NULL" +
                ");";
        try (Connection conn= connect();
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tasks tablosu oluşturuldu");
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }

        }
    }
