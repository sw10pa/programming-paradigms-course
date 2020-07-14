package PPC.model;

public class User {

    public static final String ADMINISTRATOR = "ADM";
    public static final String LECTURER = "LEC";
    public static final String STUDENT = "STU";

    private int userId;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private String status;

    public User(int userId, String firstName, String lastName, String email, String password, String status) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

}
