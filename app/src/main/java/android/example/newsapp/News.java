package android.example.newsapp;

public class News {
    private String title;
    private String author;
    private String ImageUrl;
    private String url;

    News(String t, String a, String i , String u){
        this.title=t;
        this.author=a;
        this.ImageUrl=i;
        this.url = u;
    }
    public String getTitle(){
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public String getUrl() {
        return url;
    }
}
