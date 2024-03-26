package diagnosis;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User implements Serializable {

    private static final long serialVersionUID = 2078397574496562875L;

    @Id
    @GeneratedValue(generator = "User")
    @TableGenerator(name = "User", table = "sqlite_sequence", pkColumnName = "user", valueColumnName = "seq", pkColumnValue = "User")
    private Integer id;
    private String user;
    @Lob
    private byte[] password;


    public User() {
        super();
    }
    /**
     * Constructs a User object with the specified ID, username, password, and role.
     *
     * @param id the unique identifier for the user
     * @param username the username of the user
     * @param password the password of the user
     */
    public User(Integer id, String username, byte[] password) {
        super();
        this.id = id;
        this.user = username;
        this.password = password;
    }
    /**
     * Constructs a User object with the specified username and password.
     *
     * @param username the username of the user
     * @param password the password of the user
     */
    public User(String username, byte[] password) {
        super();
        this.user = username;
        this.password = password;
    }

    /**
     * Retrieves the ID of the user.
     *
     * @return the ID of the user
     */
    public Integer getId() {
        return id;
    }
    /**
     * Sets the ID of the user.
     *
     * @param id the ID of the user
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * Retrieves the username of the user.
     *
     * @return the username of the user
     */
    public String getUser() {
        return user;
    }
    /**
     * Sets the username of the user.
     *
     * @param username the username of the user
     */
    public void setUser(String username) {
        this.user = username;
    }
    /**
     * Retrieves the password of the user.
     *
     * @return the password of the user
     */
    public byte[] getPassword() {
        return password;
    }
    /**
     * Sets the password of the user.
     *
     * @param password the password of the user
     */
    public void setPassword(byte[] password) {
        this.password = password;
    }
    /**
     * Retrieves the role of the user.
     *
     * @return the role of the user
     */


    /**
     * Retrieves the serial version UID for serialization.
     *
     * @return the serial version UID
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    /**
     * Returns a hash code value for the User object.
     *
     * @return a hash code value for the User object
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(password);
        result = prime * result + Objects.hash(user, id);
        return result;
    }
    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if ((obj == null) || (getClass() != obj.getClass()))
            return false;
        User other = (User) obj;
        return Objects.equals(user, other.user) && Objects.equals(id, other.id)
                && Arrays.equals(password, other.password);
    }
    /**
     * Returns a string representation of the User object.
     *
     * @return a string representation of the User object
     */
    @Override
    public String toString() {
        return "User{" +
                "username='" + user + '\'' +
                ", password=" + Arrays.toString(password) +
                '}';
    }
}
