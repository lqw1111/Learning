package OODesign.Kindle;

public class ReaderFactory {

    public Reader createReader(Book book){
        if (book.getFormat().equals(BookType.PDF)){
            return new PDFReader();
        } else if (book.getFormat().equals(BookType.MOBI)){
            return new MOBIReader();
        } else if (book.getFormat().equals(BookType.EPUB)){
            return new EPUBReader();
        } else
            return null;
    }
}
