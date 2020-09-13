package co.com.ceiba.mobile.pruebadeingreso.model;

import lombok.Data;

@Data
public class Post {

    private Integer id;

    private String title;

    private String body;


    public Post() {
    }

    public Post(Integer id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }
}
