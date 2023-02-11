package com.gonzik.quotes.entity;

import javax.persistence.*;

@Entity
@Table(name = "authentications")
public class AuthenticationEntity {
    @Id
    private String id;
    @Column(name = "email")
    private String login;
    private String password;
    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private UserEntity user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}
