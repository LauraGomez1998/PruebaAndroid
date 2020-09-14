package co.com.ceiba.mobile.pruebadeingreso.builder;

import co.com.ceiba.mobile.pruebadeingreso.model.Post;

public class PostBuilder {

    private Integer id;

    private String title;

    private String body;

    public PostBuilder() {
        this.id = 1;
        this.title = "Post 1";
        this.body = "I am a content";
    }

    public Post build() {
        return new Post(id, title, body);
    }
}
