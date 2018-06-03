package com.david.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

/**
 * This class is a custom implementation of UserDetails and wrapped also
 * the LoginUser class.
 */
public class UserAdapter implements UserDetails {

    /**
     * The wrapped Class LoginUser
     */
    private LoginUser user;

    /**
     * A standard Constructor
     * @param user The LoginUser which will be wrapped
     */
    public UserAdapter(LoginUser user) {
        this.user = user;
    }

    /**
     * This method returns a Collection of Authorities for the Login-System
     * @return Collection of Authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new Vector<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }

    /**
     * Gets the password of the User
     * @return The password of the user
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Gets the Username of the user
     * @return The username of the user
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * The state of the account, if it generally expired
     * @return true
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * The state of the account, if it locked (only for spring security)
     * @return true
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Gets the Credentials state, if it expired
     * @return true
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * The state of the user, if it enable
     * @return true
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * The state of the user, if it locked
     * @return true
     */
    public boolean isLocked(){
        return user.isLocked();
    }
}