package com.example.gamestore.entity;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class Users {

	@Id
    private String username;
    private String email;
    private String password;
    private String fullname;
    private String avatar;
    private Date created_date;
    private String status;

    public Users() {
    }

    public Users(String username, String email, String password, String fullname,
                 String avatar, Date createdDate, String status) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.avatar = avatar;
        this.created_date = createdDate;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreatedDate() {
        return created_date;
    }

    public void setCreatedDate(Date createdDate) {
        this.created_date = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}