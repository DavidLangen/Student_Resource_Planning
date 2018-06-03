package com.david.Entity;

import com.david.Global.UserRoles;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

/**
 * This class represents a user.
 * @author David Langen
 */
@Entity
@Table(name = "user")
public class LoginUser {

    /**
     * The unique id for the user
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * The Username of this user
     */
    @Column(nullable = false, unique = true, name = "username")
    private String username;

    /**
     * The password of the User
     */
    @Column(name = "password")
    private String password;

    /**
     * The state of the User. (Locked or Unlocked)
     */
    @Column(nullable = false, name = "locked")
    private boolean locked = false;

    /**
     * The role of the User
     */
    @Column(name = "role")
    protected String role;

    /**
     * A special constructor filling all properties of a user object
     * apart from locked, which will be set to false.
     * @param username  The username of the User
     * @param password  The password of the User (will be crypted)
     * @param role      The role of the User
     */
    public LoginUser(String username, String password, UserRoles role) {
        this(username,password,role,false);
    }
    /**
     * A standard constructor filling all properties of a user object.
     * @param username  The username of the User
     * @param password  The password of the User (will be crypted)
     * @param role      The role of the User
     * @param locked    The lock state of the User
     */
    public LoginUser(String username, String password, UserRoles role, boolean locked) {
        this.username = username;
        setPassword(password);
        this.locked = locked;
        this.role = role.toString();
    }

    /**
     *  A default constructor
     */
    public LoginUser() {
    }

    /**
     * Gets the id of this user.
     *
     * @return The id of this user.
     */
    public Long getId() {
        return id;
    }

    /**
     * This Method lock the user for the Login-System
     */
    public void lock() {
        this.locked = true;
    }

    /**
     * This Method unlock the user for the Login-System
     */
    public void unlock() {
        this.locked = false;
    }

    /**
     * This Method lock or unlock the user for the Login-System,
     * depending on the state of the user
     */
    public void toggleLocking(){
        if(this.locked) unlock();
        else lock();
    }

    /**
     * Gets the lock state of the user
     * @return The lock state of the user
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * Gets the Username of the user
     * @return The username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Change the username of the user
     * @param username The username to be set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the crypted password of the user
     * @return the crypted password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user and crypted it
     * @param password the password in clear text
     */
    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    /**
     * Gets the role of the user
     * @return The role of the user
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the user
     * @param role The role to be set
     */
    public void setRole(String role) {
        this.role = role;
    }
}
