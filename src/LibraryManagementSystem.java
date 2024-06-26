import java.util.Scanner;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;


public class LibraryManagementSystem {

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        LibraryManagementSystem libraryObj = new LibraryManagementSystem();

        BookService BookServiceObject  = new BookService();
        MemberService MemberServiceObject = new MemberService();

        //Display some introductory text
        System.out.println("Welcome to the Library Management System!");
        System.out.println("Please enter your username and password  to access your account.");
        System.out.println();
  
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        
        String logInCorrect = "login correct";
        String option = "";
        // CHECK IF LOGIN DETAILS ARE CORRECT
        while (!option.equals(logInCorrect))
        {
            if (libraryObj.isLibrarianRegistered(username, password))
            {
                break;
            }
            else
            { 
                System.out.println();
                System.out.println("Login details incorrect.");
                System.out.println();
                System.out.print("Username: ");
                username = scanner.nextLine();

                System.out.print("Password: ");
                password = scanner.nextLine();
            }
        }
        System.out.println();
        while (!option.equalsIgnoreCase("quit"))
        {
                //Display options
                System.out.println("Select your option from the following:");
                System.out.println("1. Books");
                System.out.println("2. Members");
                System.out.println("3. BorrowBooks");
                System.out.print("Input option here: ");
                option = scanner.nextLine();
                System.out.println();

                if (option.equalsIgnoreCase("1"))
                {
                    // MANAGE BOOKS
                    libraryObj.manage_books(scanner, BookServiceObject);
                }
                else if (option.equalsIgnoreCase("2"))
                {
                    // MANAGE MEMBERS
                    libraryObj.manage_members(scanner, MemberServiceObject);

                }
                else if (option.equals("3"))
                {
                    // MANAGE BORROWED BOOKS
                    libraryObj.manage_borrowedBooks(scanner, BookServiceObject);
                }

                System.out.println();
                System.out.println("Goodbye.");

        }


        //close necessary operations
        scanner.close();

    }



    // Methods

    public boolean isLibrarianRegistered(String username, String password)
    {
        LibrarianService service = new LibrarianService();
        Librarian info = service.getLibrarian(username, password);
        
        if (info == null)
        {   
            return false;
        }

        return true;
    }
    public void borrowBook(Scanner s, BookService bookService)
    {
        System.out.print("Input member's ID: ");
        int memberID = s.nextInt();
        s.nextLine();

        System.out.print("Input borrowed book ID: ");
        int bookID = s.nextInt();
        s.nextLine();

        System.out.print("Input issue date(yyyy-MM-dd): ");
        Date IssueDate = Date.valueOf(s.nextLine());

        System.out.print("Input return date(yyyy-MM-dd)");
        Date returnDate = Date.valueOf(s.nextLine());

        // Process the transaction/ Add to borrowed database
        BorrowedBookService serviceObj = new BorrowedBookService();

        //step 1: check book availability then update
        Book book = bookService.getBook(bookID);

        if (book.get_copiesAvailable() > 0){
            book.set_copiesAvailable(book.get_copiesAvailable()-1);
            bookService.updateBook(book);

            serviceObj.borrowBook(bookID, memberID, IssueDate, returnDate);
        }
        else
        {
            System.out.println("There are no copies available for the book "+ book.get_title());
        }

    }
    public void manage_borrowedBooks(Scanner s, BookService bookService)
    {
        System.out.println("Select option");
        System.out.println("1.  Borrow a book");
        System.out.println("2.  Return a book");
        System.out.print("Input option: ");
        String option = s.nextLine();
        System.out.println();

        if(option.equals("1"))
        {
            // BORROW A BOOK
            while(!option.equals("no"))
            {   
                borrowBook(s, bookService);
                System.out.print("Would you like to borrow another book? (yes/no): ");
                option = s.nextLine();
                System.out.println();
            }
            
        }
        else if (option.equals("2"))
        {
            // RETURN A BOOK

            while(!option.equals("no"))
            {
                System.out.print("Input member's ID: ");
                int memberID = s.nextInt();
                s.nextLine();

                System.out.print("Input borrowed book ID: ");
                int bookID = s.nextInt();
                s.nextLine();

                Book book = bookService.getBook(bookID);
                book.set_copiesAvailable(book.get_copiesAvailable()+1);
                bookService.updateBook(book);
                
                System.out.println("Thank you for returning the book.");
                System.out.print("Would you like to return another book? (yes/no): ");
                option = s.nextLine();

                System.out.println();
            }
        }

    }
    public void manage_members(Scanner s, MemberService serviceObj)
    {
        System.out.println("Select option: ");
        System.out.println("1. Add a New Member");
        System.out.println("2. Verify a Member");
        System.out.println("3. Delete a Member");
        System.out.print("Input option here: ");

        String option = s.nextLine();
        System.out.println();

        if (option.equals("1"))
        {
            // ADD A NEW MEMBER
            System.out.println();
            System.out.println("Please input the member details in the following format: Member ID, Name, Address, Phone, Email, Membership Date (yyyy-MM-dd)");

            String memberDetails = s.nextLine();
            String[] values  = memberDetails.split(",");

            String string_date = values[5];
            java.sql.Date sqlDate = null;

            //convert the string to sql date
            java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");

            try {
                java.util.Date utilDate = format.parse(string_date);
                sqlDate = new java.sql.Date(utilDate.getTime());

            } catch (ParseException e) {
                e.printStackTrace();
            }

            java.sql.Date membershipDate = sqlDate;


            // record the data
            serviceObj.addMember(new Member(Integer.parseInt(values[0].strip()), values[1].strip(), values[2].strip(), values[3].strip(), values[4].strip(), membershipDate));

        }
        else if (option.equals("2"))
        {
            // VERIFY A MEMBER
            System.out.println();
            System.out.print("Input the member's ID:");
            
            int memberID = s.nextInt();
            s.nextLine();
            System.out.println();

            Member member = serviceObj.getMember(memberID);

            if (member != null)
            {
                // Print some of the member's details
                System.out.println("Member ID: "+ memberID);
                System.out.println("Name     : "+ member.get_name());
                System.out.println("Address  : "+ member.get_address());
                System.out.println("Phone    : "+ member.get_phone());
                System.out.println("Email  : "+ member.get_email());
                System.out.println("Membership Date: "+ member.get_membershipDate());
            }

        }
        else if (option.equals("3"))
        {
            // DELETE A MEMBER
            System.out.print("Input member ID:");

            int memberID = s.nextInt();
            s.nextLine();
            System.out.println();

            serviceObj.deleteMember(memberID);
        }

    }
    public void manage_books(Scanner s, BookService serviceObj)
    {
        System.out.println("Please select an option from the following choices to manage our collection of books.");
        System.out.println("1. Add a New Book");
        System.out.println("2. Update Existing Book");
        System.out.println("3. Delete a Book");
        System.out.println("4. Search for a Book");
        System.out.println("5. Display All Available Books");

        System.out.print("Input option here: ");
        String option = s.nextLine();
        System.out.println();

        if (option.equalsIgnoreCase("1"))
        {
            // ADD A NEW BOOK
            System.out.println();
            System.out.println("Please input the book details in the following format: Title, Author, Genre, Published date(yyyy-MM-dd), ISBN, Copies Available");
            
            String book_details = s.nextLine();
            String[] values = book_details.split(",");

            //record the data
            
            String title = values[0];
            String author = values[1];
            String genre = values[2];

            String string_date = values[3];
            java.sql.Date sqlDate = null;

            //convert the string to sql date
            java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");

            try {
                java.util.Date utilDate = format.parse(string_date);
                sqlDate = new java.sql.Date(utilDate.getTime());

            } catch (ParseException e) {
                e.printStackTrace();
            }

            java.sql.Date publishedDate = sqlDate;
            String isbn = values[4];
            int copiesAvailable = Integer.parseInt(values[5].trim());

            serviceObj.addBook(new Book(title, author, genre, publishedDate, isbn, copiesAvailable));

        }

        else if (option.equalsIgnoreCase("2"))
        {
            //UPDATE EXISTING BOOK
            System.out.println();
            System.out.println("You are authorized to make updates to the following book information fields:");
            System.out.println("1. Title: Correct any errors or updates if the book has been reissued under different title.");
            System.out.println("2. Author: Update to correct erros or to add/remove contributors.");
            System.out.println("3. Genre: Update to reflect reclassification or more accurately describe the book's content.");
            System.out.println("4. Copies Available: Update as books are checked out, returned, or new copies are added to the library.");
            System.out.println();
            System.out.println("Use the following format to input details:  Book ID, Title/null, Author/null, Genre/null, Copies Available/null");
            System.out.println("Note the \"null\" value implies that you don't want to an update to a specific field.");
            
            //accpet user's input
            option = s.nextLine();

            String[] values = option.split(",");

            //capture the book details
            String bookID = values[0].trim();
            Book book = serviceObj.getBook(Integer.parseInt(bookID));

            if (book == null)
            {
                System.out.println("Book ID doesn't exist!!!!!");
                System.out.println("Please Check For Any Error In the provide Book ID or Enter an existing ID");
            }
            else{
                String title = values[1].trim();
                String author = values[2].trim();
                String genre = values[3].trim();
                String copiesAvailable = values[4].trim();

                if (!title.equalsIgnoreCase("null"))
                {
                    book.set_title(title);
                }
                if (!author.equalsIgnoreCase("null"))
                {
                    book.set_author(author);
                }
                if (!genre.equalsIgnoreCase("null"))
                {
                    book.set_genre(genre);
                }
                if (!copiesAvailable.equalsIgnoreCase("null"))
                {
                    book.set_copiesAvailable(Integer.parseInt(copiesAvailable));
                }
            }

            serviceObj.updateBook(book);
        }
        else if (option.equalsIgnoreCase("3"))
        {
            // DELETE A BOOK

            System.out.print("Insert a Book ID: ");
            option = s.nextLine();
            System.out.println();

            //user input
            int bookID = Integer.parseInt(option.trim());

            serviceObj.deleteBook(bookID);
        }

        else if (option.equalsIgnoreCase("4"))
        {
            // SEARCH FOR A BOOK
            System.out.print("Insert a Book ID: ");

            option = s.nextLine();
            System.out.println();
            
            Book book = serviceObj.getBook(Integer.parseInt(option.trim()));

            if ( book != null)
            {
                System.out.println("Book ID : "+ book.getID());
                System.out.println("Title   : "+ book.get_title());
                System.out.println("Author  : "+ book.get_author());
                System.out.println("Genre   : "+ book.get_genre());

                System.out.println("Published Date: "+ book.get_published_date());
                System.out.println("ISBN    : "+ book.get_isbn());
                System.out.println("Copies Available: "+ book.get_copiesAvailable());
            }

            else{
                System.out.println("Book Not Found!!!!!!!!");
            }
        }
        else if (option.equals("5"))
        {
            // DISPLAY ALL AVAILABLE BOOKS
            List<Book> books = serviceObj.getAllBooks();

            Book book = null;
            for (int i=0; i < books.size(); i++)
            {
                book = books.get(i);
                if (book.get_copiesAvailable() > 0)
                {
                    System.out.println("Book ID : "+ book.getID());
                    System.out.println("Title   : "+ book.get_title());
                    System.out.println("Author  : "+ book.get_author());
                    System.out.println("Genre   : "+ book.get_genre());

                    System.out.println("Published Date: "+ book.get_published_date());
                    System.out.println("ISBN    : "+ book.get_isbn());
                    System.out.println("Copies Available: "+ book.get_copiesAvailable());
                }

                System.out.println();
            }
        }
    }

}
