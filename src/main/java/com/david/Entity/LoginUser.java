package com.david.Entity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class LoginUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true, name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(nullable = false, name = "locked")
    private boolean locked = false;

    @Column(name = "role")
    private String role;

    public LoginUser(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.locked = false;
    }

    public LoginUser(String username, String password, String role, boolean locked) {
        this.username = username;
        this.password = password;
        this.locked = locked;
        this.role = role;
    }

    public LoginUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void lock() {
        this.locked = true;
    }

    public void unlock() {
        this.locked = false;
    }

    public void toggleLocking(){
        if(this.locked) unlock();
        else lock();
    }

    public boolean isLocked() {
        return locked;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
