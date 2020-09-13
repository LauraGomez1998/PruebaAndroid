package co.com.ceiba.mobile.pruebadeingreso.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable {

    private Integer id;

    private String name;

    private String username;

    private String email;

    private String phone;

    private String website;

    public User() {
    }

    public User(Integer id, String name, String username, String email, String phone, String website) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.website = website;
    }
}

