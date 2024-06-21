import java.sql.Date;

public class Book {

    private int bookId;
    private String title;
    private String author;
    private String genre;
    private Date publishedDate;
    private String isbn;
    private int copiesAvailable;

    // constructor

    public Book(int id, String title, String author, String genre, Date date, String isbn, int copiesAvailable)
    {
        bookId = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        publishedDate = date;
        this.isbn = isbn;
        this.copiesAvailable = copiesAvailable;
    }
    // Getters and Setters
    public int    getID(){ return bookId;}
    public String get_title(){ return title;}
    public String get_author(){ return author; }
    public String get_genre() { return genre;}
    public Date   get_published_date() { return publishedDate;}
    public String get_isbn() { return isbn;}
    public int    get_copiesAvailable() {return copiesAvailable;}

    public void set_copiesAvailable(int num)
    {
        this.copiesAvailable = num;
    }

    public void set_title(String title)
    {
        this.title = title;
    }

    public void set_author(String author)
    {
        this.author = author;
    }
    public void set_genre(String genre)
    {
        this.genre = genre;
    }



}
