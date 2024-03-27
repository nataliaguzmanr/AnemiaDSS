package diagnosis;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;


public class User implements Serializable {



    private static final long serialVersionUID = 2078397574496562875L;

    private Integer id;
    private String user;

    private String password;


    public User(Integer id, String username, String password) {
        this.id = id;
        this.user = username;
        this.password = password;
    }


    /**
     * Constructs a User object with the specified username and password.
     * @param username the username of the user
     * @param password the password of the user
     */
    public User(String username, String password) {
        super();
        this.user = username;
        this.password = password;
    }



    /**
     * Retrieves the ID of the user.
     * @return the ID of the user
     */
    public Integer getId() {
        return id;
    }


    /**
     * Sets the ID of the user.
     * @param id the ID of the user
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the username of the user.
     * @return the username of the user
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the username of the user.
     * @param username the username of the user
     */
    public void setUser(String username) {
        this.user = username;
    }

    /**
     * Retrieves the password of the user.
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * @param password the password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Retrieves the serial version UID for serialization.
     * @return the serial version UID
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user1 = (User) o;
        return Objects.equals(id, user1.id) && Objects.equals(user, user1.user) && Objects.equals(password, user1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
