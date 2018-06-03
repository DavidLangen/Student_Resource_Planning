package com.david.Global;

public enum UserRoles {

    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private String role;

    UserRoles(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
