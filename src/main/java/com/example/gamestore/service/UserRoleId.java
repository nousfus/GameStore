package com.example.gamestore.service;

import java.io.Serializable;
import java.util.Objects;

public class UserRoleId implements Serializable {

    private String username;
    private String role_id;

    public UserRoleId() {
    }

    public UserRoleId(String username, String role_id) {
        this.username = username;
        this.role_id = role_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, role_id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof UserRoleId))
            return false;

        UserRoleId other = (UserRoleId) obj;

        return Objects.equals(username, other.username)
                && Objects.equals(role_id, other.role_id);
    }
}