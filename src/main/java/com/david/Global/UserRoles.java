package com.david.Global;

/**
 * An enum defining all roles in the system.
 */
public enum UserRoles {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    /**
     * Holds this value represented as a String.
     */
    private String role;

    /**
     * A constructor initializing a UserRole.
     *
     * @param role The String representation of this UserRole.
     */
    UserRoles(String role) {
        this.role = role;
    }

    /**
     * Retrieves the String representation of this enum value.
     *
     * @return This enum value as String.
     */
    @Override
    public String toString() {
        return role;
    }
}
