package OODesign.Kindle;

public class Book {
    private BookType format;
    private String title;
    private String content;

    public BookType getFormat() {
        return format;
    }

    public void setFormat(BookType format) {
        this.format = format;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
