package com.order_management.ordermanagement.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "admins")
public class Admin implements Entity<String> {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "password")
    private String password;

    public Admin() {}

    public Admin(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public Admin(String password) {
        this.password = password;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String s) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
