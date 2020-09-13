package co.com.ceiba.mobile.pruebadeingreso.local.model;

import co.com.ceiba.mobile.pruebadeingreso.model.Post;
import co.com.ceiba.mobile.pruebadeingreso.remote.model.post.GetUserPostsResp;
import co.com.ceiba.mobile.pruebadeingreso.remote.model.user.GetUsersResp;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PostEntity extends RealmObject {

    @PrimaryKey
    private Integer id;

    private String title;

    private String body;


    public PostEntity() {
    }

    public void mapFromGetUserPostsResp(GetUserPostsResp userPostsResp) {
        this.setId(userPostsResp.getId());
        this.setTitle(userPostsResp.getTitle());
        this.setBody(userPostsResp.getBody());
    }

    public void mapToPostDomain(Post post) {
        post.setId(this.getId());
        post.setTitle(this.getTitle());
        post.setBody(this.getBody());
    }

}
