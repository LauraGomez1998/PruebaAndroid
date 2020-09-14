package co.com.ceiba.mobile.pruebadeingreso.builder;

import co.com.ceiba.mobile.pruebadeingreso.model.User;

public class UserBuilder {

    private Integer id;

    private String name;

    private String username;

    private String email;

    private String phone;

    private String website;

    public UserBuilder() {
        this.id = 1;
        this.name = "Laura";
        this.username = "LauraMGC";
        this.email = "lauragomez.lg247@gmail.com";
        this.phone = "3206860034";
        this.website = "https://blabla.com";
    }

    public User build() {
        return new User(id, name, username, email, phone, website);
    }
}
