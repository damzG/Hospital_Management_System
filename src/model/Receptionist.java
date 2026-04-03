//Oyindamola Olaosun C00313475 OOSD Project

package model;

/**
 * Represents a receptionist staff member in the hospital management system.
 * Receptionists are the primary users of the system — they handle patient
 * registration, appointment booking, and prescription recording.
 *
 * <p>The {@code receptionistId} acts as a foreign key in the
 * {@code patient}, {@code appointment}, and {@code prescription} tables,
 * linking every record back to the receptionist who created it.</p>
 */

public class Receptionist {

    /** Unique identifier for this receptionist (DB-generated). */
    private int receptionistId;

    /** Full name of the receptionist. */
    private String name;

    /** Email address of the receptionist. */
    private String email;

    /** Contact phone number of the receptionist. */
    private String phone;

    /** Login username for system access. */
    private String username;

    /** Login password for system access. */
    private String password;

    // Default constructor
    /**
     * Default no-argument constructor.
     * Required for cases where fields are set individually after construction.
     */
    public Receptionist() {}

    /**
     * Constructs a Receptionist with all fields populated.
     * Used when loading an existing receptionist record from the database
     * or when creating a fully specified receptionist object.
     *
     * @param receptionistId the receptionist's unique database ID
     * @param name           the receptionist's full name
     * @param email          the receptionist's email address
     * @param phone          the receptionist's contact phone number
     * @param username       the receptionist's system login username
     * @param password       the receptionist's login password
     */


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
    /**
     * Returns the receptionist's unique database ID.
     * This value is used as a foreign key in patient, appointment,
     * and prescription records.
     *
     * @return the receptionist ID
     */

    public int getReceptionistId() {
        return receptionistId;
    }

    /**
     * Sets the receptionist's unique ID.
     * Typically called after a database insert returns the generated key.
     *
     * @param receptionistId the ID to assign
     */
    public void setReceptionistId(int receptionistId) {
        this.receptionistId = receptionistId;
    }

    /**
     * Returns the receptionist's full name.
     * Displayed in the welcome message on {@code MainMenuScreen}.
     *
     * @return the full name
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the receptionist's full name.
     *
     * @param name the new full name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the receptionist's email address.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Updates the receptionist's email address.
     *
     * @param email the new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the receptionist's contact phone number.
     *
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Updates the receptionist's contact phone number.
     *
     * @param phone the new phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns the receptionist's system login username.
     *
     * @return the username
     */

    public String getUsername() {
        return username;
    }

    /**
     * Updates the receptionist's system login username.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the receptionist's login password.
     *
     * @return the password
     */

    public String getPassword() {
        return password;
    }

    /**
     * Updates the receptionist's login password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns a human-readable representation of the receptionist.
     * Password is intentionally excluded from output for security.
     *
     * @return a string showing ID, name, email, phone, and username
     */

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