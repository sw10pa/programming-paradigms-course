package PPC.model;

public class Lecturer {

    private final int lecturerId;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;

    public Lecturer(int lecturerId, String firstName, String lastName, String email, String password) {
        this.lecturerId = lecturerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public int getLecturerId() {
        return lecturerId;
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

}
