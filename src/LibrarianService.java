
public class LibrarianService {

    private LibrarianDAO librarianDAO = new LibrarianDAO();

    public void addLibrarian(Librarian librarian) {
        librarianDAO.addLibrarian(librarian);
    }

    public void updateLibrarianDetails(Librarian librarian) {
        librarianDAO.updateLibrarianDetails(librarian);
    }

    public void deleteLibrarian(String username, String password) {
        librarianDAO.deleteLibrarian(username, password);
    }

    public Librarian getLibrarian(String username, String password) {
        return librarianDAO.getLibrarian(username, password);
    }
}
