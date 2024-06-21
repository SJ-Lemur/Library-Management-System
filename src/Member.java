import java.sql.Date;

public class Member {

    private int memberId;
    private String name;
    private String address;
    private String phone;
    private String email;
    private Date membershipDate;

    // Constructor
    public Member(int id, String name, String address, String phone, String email, Date date)
    {
        memberId = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        membershipDate = date;

    }

    // Getters and Setters

    public int get_id(){ return memberId; }
    public String get_name() { return name;}
    public String get_address() { return address;}
    public String get_phone() { return phone;}
    public String get_email() { return email;}
    public Date get_membershipDate() { return membershipDate; }


}
