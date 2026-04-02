package model;

public class Receptionist {

    private int receptionistId;
    private String name;
    private String email;
    private String phone;
    private String username;
    private String password;

    // Default constructor
    public Receptionist() {}

    // Parameterized constructor
    public Receptionist(int receptionistId, String name, String email,
                        String phone, String username, String password) {
        this.receptionistId = receptionistId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public int getReceptionistId() {
        return receptionistId;
    }

    public void setReceptionistId(int receptionistId) {
        this.receptionistId = receptionistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Receptionist{" +
                "receptionistId=" + receptionistId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}