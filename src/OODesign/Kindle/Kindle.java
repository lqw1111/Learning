package OODesign.Kindle;

import java.util.ArrayList;
import java.util.List;

public class Kindle {
    private List<Book> library;
    private ReaderFactory readerFactory;

    public Kindle(){
        this.readerFactory = new ReaderFactory();
        this.library = new ArrayList<>();
    }

    public void upLoad(Book book){
        library.add(book);
    }

    public void removeBook(Book book){
        library.remove(book);
    }

    public void downloadBook(Book book){
        library.add(book);
    }

    public void read(Book book){
        Reader reader = readerFactory.createReader(book);
        reader.display(book);
    }

}
