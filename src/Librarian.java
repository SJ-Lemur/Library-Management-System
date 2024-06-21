public class Librarian {
    private int librarianId;
    private String name;
    private String username;
    private String password;

    public Librarian(int id, String name, String username, String password)
    {
        librarianId = id;
        this.name = name;
        this.username = username;
        this.password = password;

    }

    // Getters and Setters
    
    public int get_id() { return librarianId;}
    public String get_name() { return name;}
    public String get_username() { return username;}
    public String get_password() { return password;}

    public void set_username(String new_username)
    {
        username = new_username;
    }

    public void set_password(String new_password)
    {
        password = new_password;
    }
}
