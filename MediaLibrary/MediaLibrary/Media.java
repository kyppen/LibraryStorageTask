package MediaLibrary;

public abstract class Media {


    private String id;
    private String title;
    private String genre;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public String toString() {
        return "ID = " + id + " title = " + title + " genre = " + genre;
    }
}
