package co.com.ceiba.mobile.pruebadeingreso.local.model;

import co.com.ceiba.mobile.pruebadeingreso.model.User;
import co.com.ceiba.mobile.pruebadeingreso.remote.model.user.GetUsersResp;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends RealmObject {

    @PrimaryKey
    private Integer id;

    private String name;

    private String username;

    private String email;

    private String phone;

    private String website;

    private RealmList<PostEntity> postsList = new RealmList<>();


    public UserEntity() {
    }

    public void mapFromUserRemote(GetUsersResp userRemote) {
        this.setId(userRemote.getId());
        this.setName(userRemote.getName());
        this.setUsername(userRemote.getUsername());
        this.setEmail(userRemote.getEmail());
        this.setPhone(userRemote.getPhone());
        this.setWebsite(userRemote.getWebsite());
    }

    public void mapToUserDomain(User user) {
        user.setId(this.getId());
        user.setName(this.getName());
        user.setUsername(this.getUsername());
        user.setEmail(this.getEmail());
        user.setPhone(this.getWebsite());
    }

}
