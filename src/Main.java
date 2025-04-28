import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseHelper.createTasksTable(); // Tabloyu kuruyoruz
        boolean running = true;
        int id;
        String status;
        while (running) {
            System.out.println("\n=== Görev Yönetimi ===");
            System.out.println("1. Görev Ekle");
            System.out.println("2. Görevleri Listele");
            System.out.println("3. Görev Sil");
            System.out.println("4. Görev durumu güncelle");
            System.out.println("5. Görev bilgilerini güncelle");
            System.out.println("6. Çıkış");
            System.out.println("7. Aktif Görevleri Listele");
            System.out.println("8. Tamamlanan Görevleri Listele");
            System.out.println("9. Başlığa Göre Görev Ara");
            System.out.println("10. Görevleri Dosyaya Kaydet");
            System.out.println("11. Dosyadan Görevleri Yükle");
            System.out.print("Seçiminiz: ");
            try{
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Başlık: ");
                    String title = scanner.nextLine();
                    System.out.print("Açıklama: ");
                    String description = scanner.nextLine();
                    System.out.print("Durum (aktif/tamamlandı): ");
                    status = scanner.nextLine();
                    Task newTask = new Task(0, title, description, status);
                    TaskDao.addTask(newTask);
                    break;
                case 2:
                    List<Task> tasks = TaskDao.getAllTasks();
                    for (Task task : tasks) {
                        System.out.println("ID: " + task.getId() + ", Başlık: " + task.getTitle() + ", Açıklama:" + task.getDescription() +", Durum: " + task.getStatus());
                    }
                    break;
                case 3:
                    System.out.print("Silinecek Görev ID'si: ");
                    id = scanner.nextInt();
                    Task tasktoDelete = TaskDao.getTaskById(id);
                    if (tasktoDelete != null)
                        TaskDao.deleteTask(id);
                    else
                        System.out.println("Silinecek kayıt bulunamadı.");
                    break;
                case 4:
                    System.out.print("Güncellenecek Görev ID'si: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    Task tasktoUpdate = TaskDao.getTaskById(id);
                    if (tasktoUpdate != null){
                        System.out.print("Status bilgisi: ");
                        String status2 = scanner.nextLine();
                        TaskDao.updateStatus(id,status2);
                    } else
                        System.out.println("Güncellenecek ID bulunamadı.");
                    break;
                case 5:
                    System.out.println("Güncellenecek görevin ID bilgisi: ");
                    id = scanner.nextInt();
                    scanner.nextLine(); // dummy read
                    Task tasktoAllUpdate = TaskDao.getTaskById(id);
                    if (tasktoAllUpdate != null){
                        System.out.println("Yeni başlık: ");
                        title = scanner.nextLine();
                        System.out.println("Yeni açıklama: ");
                        description = scanner.nextLine();
                        System.out.println("Yeni durum: ");
                        status = scanner.nextLine();
                        Task task = new Task(id, title, description, status);
                        TaskDao.updateTask(task);
                    } else
                        System.out.println("Güncellenecek ID bulunamadı.");
                    break;
                case 6:
                    running = false;
                    break;
                case 7:
                    List<Task> activeTasks = TaskDao.getTasksByStatus("Aktif");
                    for (Task task : activeTasks) {
                        System.out.println("ID: " + task.getId() + ", Başlık: " + task.getTitle() + ", Açıklama:" + task.getDescription() +", Durum: " + task.getStatus());
                    }
                    break;
                case 8:
                    List<Task> completedTasks = TaskDao.getTasksByStatus("Tamamlandı");
                    for (Task task : completedTasks) {
                        System.out.println("ID: " + task.getId() + ", Başlık: " + task.getTitle() + ", Açıklama:" + task.getDescription() +", Durum: " + task.getStatus());
                    }
                    break;
                case 9:
                    System.out.print("Aranacak kelime: ");
                    String keyword = scanner.nextLine();
                    List<Task> foundTasks= TaskDao.searchTasksByTitle(keyword);
                    for (Task task : foundTasks) {
                        System.out.println("ID: " + task.getId() + ", Başlık: " + task.getTitle() + ", Açıklama:" + task.getDescription() +", Durum: " + task.getStatus());
                    }
                    if(foundTasks.isEmpty())
                        System.out.println("Aramaya uygun görev bulunamadı");
                    break;
                case 10:
                    System.out.println("Kaydedilecek dosya adı(örnek: tasks.csv):");
                    String saveFilename = scanner.nextLine();
                    TaskDao.saveTaskstoFile(saveFilename);
                    break;
                case 11:
                    System.out.println("Yüklenecek dosya adı (örnek: tasks.csv");
                    String loadFilename = scanner.nextLine();
                    TaskDao.loadTasksFromFile(loadFilename);
                    break;
                default:
                    System.out.println("Geçersiz seçim!");
            }
        }catch (NumberFormatException e){
            System.out.println("Lütfen bir sayı girin");
        }
        }
        scanner.close();
    }
}
