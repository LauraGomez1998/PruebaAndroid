package co.com.ceiba.mobile.pruebadeingreso.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.local.model.PostEntity;
import co.com.ceiba.mobile.pruebadeingreso.local.model.UserEntity;
import co.com.ceiba.mobile.pruebadeingreso.model.Post;
import co.com.ceiba.mobile.pruebadeingreso.model.User;
import co.com.ceiba.mobile.pruebadeingreso.remote.UserService;
import co.com.ceiba.mobile.pruebadeingreso.remote.model.post.GetUserPostsResp;
import co.com.ceiba.mobile.pruebadeingreso.remote.model.user.GetUsersResp;
import io.reactivex.Single;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Component ID: UserRepository.java <br>
 * Description: Main implementation for {@link UserRepository}<br>
 * Author: <a href="mailto:lagomez@heinsohn.com.co">Laura Gómez</a> <br>
 * <p/>
 * Revision Change
 * <table>
 * <tr>
 * <th>Author</th><th>Date</th><th>Version</th><th>Change-Description</th>
 * </tr>
 * <tr>
 * <td>Laura Gómez</td><td>9/12/2020</td><td>1.0</td><td>Initial</td>
 * </tr>
 * </table>
 */
public class UserRepositoryImpl implements UserRepository {

    private UserService userService;

    @Inject
    public UserRepositoryImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Single<List<User>> getUserList() {
        return userService.getUsersList().map(response -> {
            List<User> userList = new ArrayList<>();
            try (Realm realm = Realm.getDefaultInstance()) {
                List<UserEntity> entities = new ArrayList<>();
                for (GetUsersResp userResp : response) {

                    //Map from remote object
                    UserEntity userEntity = new UserEntity();
                    userEntity.mapFromUserRemote(userResp);
                    entities.add(userEntity);

                    //Map to domain object
                    User user = new User();
                    userEntity.mapToUserDomain(user);
                    userList.add(user);
                }

                //Insert user list
                realm.executeTransaction(r -> r.insertOrUpdate(entities));
            }

            return userList;
        });
    }

    @Override
    public Single<List<Post>> getUserPostsList(Integer userId) {
        return userService.getUserPostsList(userId).map(response -> {
            List<Post> postList = new ArrayList<>();
            try (Realm realm = Realm.getDefaultInstance()) {
                RealmList<PostEntity> entities = new RealmList<>();
                for (GetUserPostsResp postsResp : response) {
                    //Map from remote object
                    PostEntity postEntity = new PostEntity();
                    postEntity.mapFromGetUserPostsResp(postsResp);
                    entities.add(postEntity);

                    //Map to domain object
                    Post post = new Post();
                    postEntity.mapToPostDomain(post);
                    postList.add(post);
                }

                UserEntity userEntity = realm.where(UserEntity.class).equalTo("id", userId).findFirst();
                realm.executeTransaction(r -> {
                    // Insert posts
                    r.insertOrUpdate(entities);

                    //Update the user's posts
                    userEntity.getPostsList().deleteAllFromRealm();
                    userEntity.getPostsList().addAll(entities);
                    r.insertOrUpdate(userEntity);
                });
            }
            return postList;
        });
    }

    @Override
    public List<User> getLocalUserList() {
        List<User> userList = new ArrayList<>();
        try (Realm realm = Realm.getDefaultInstance()) {
            RealmResults<UserEntity> userRealmList = realm.where(UserEntity.class).findAll();

            for (UserEntity userEntity : userRealmList) {
                User user = new User();
                userEntity.mapToUserDomain(user);
                userList.add(user);
            }
        }

        return userList;
    }
}
