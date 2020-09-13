package co.com.ceiba.mobile.pruebadeingreso.usecase;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.model.Post;
import co.com.ceiba.mobile.pruebadeingreso.model.User;
import io.reactivex.Single;

public interface UserUseCase {

    Single<List<User>> getUserList();

    Single<List<Post>> getUserPostsList(Integer userId);
}
