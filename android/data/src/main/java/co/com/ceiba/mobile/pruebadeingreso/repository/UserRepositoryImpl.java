package co.com.ceiba.mobile.pruebadeingreso.repository;

import android.util.Log;

import com.google.gson.Gson;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.platform.UserService;
import io.reactivex.Single;

public class UserRepositoryImpl implements UserRepository {

    private UserService userService;

    @Inject
    public UserRepositoryImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Single<String> getUserList() {
        return userService.getUsersList().flatMap(getUsersResp -> {
            Log.d("prueba", new Gson().toJson(getUsersResp));
            return Single.just("SUCCESS");
        });
    }
}
