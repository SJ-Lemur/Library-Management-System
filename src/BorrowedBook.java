import java.math.BigDecimal;
import java.util.Date;

public class BorrowedBook {

     private int transactionId;
    private int bookId;
    private int memberId;
    private Date issueDate;
    private Date returnDate;
    private BigDecimal fine;

    //Constructor
    public BorrowedBook(int transactionId, int bookId, int memberId, Date issueDate, Date returnDate, BigDecimal fine)
    {
        this.transactionId = transactionId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.fine = fine;
    }

    // Getters and Setters

    public int get_transactionId() { return transactionId;}
    public int get_bookId() { return bookId;}
    public int get_memberId() { return memberId;}
    public Date get_issueDate() { return issueDate;}
    public Date get_returnDate() { return returnDate;}
    public BigDecimal get_fine() { return fine;}

    public void set_fine(BigDecimal f)
    {
        fine = f;
    }
}
