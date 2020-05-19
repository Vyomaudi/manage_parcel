package com.courier.login;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "LoginTable")
public class LoginTable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @Column(name = "UserName",unique=true)
    String username;

    @NotNull
    @Column(name = "Password")
    String password;

    @Column(name = "Special")
    String special;

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public LoginTable(){

    }

    public LoginTable(@NotNull String username, @NotNull String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
