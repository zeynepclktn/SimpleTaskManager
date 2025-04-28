public class Task {
    private int id;
    private String title;
    private String description;
    private String status;
    public Task(int id, String title, String description, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    //Getter and setter methods

    public int getId(){
        return id;
    }
    public void setId(int id) {
        this.id= id;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title) {
        this.title= title;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description) {
        this.description= description;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status) {
        this.status= status;
    }
}