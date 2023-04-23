package main.Comment;

public class Comment {
    private String name;
    private String date;
    private String content;
    private String user;
    Comment(String name,String date,String content,String user){
        this.name = name;
        this.date = date;
        this.content = content;
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getUser() {
        return user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
