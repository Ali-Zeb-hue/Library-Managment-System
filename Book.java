public class Book {
    private String id, title, author, publisher, year, isbn, copies;

    public Book(String id, String title, String author, String publisher, String year, String isbn, String copies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.isbn = isbn;
        this.copies = copies;
    }

    public String[] getBookDetails() {
        return new String[]{id, title, author, publisher, year, isbn, copies};
    }

    public String getId() {
        return id;
    }

    public void updateBook(String title, String author, String publisher, String year, String isbn, String copies) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.isbn = isbn;
        this.copies = copies;
    }
}
