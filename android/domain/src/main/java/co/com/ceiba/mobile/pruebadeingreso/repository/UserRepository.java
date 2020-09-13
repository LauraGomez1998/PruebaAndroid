package co.com.ceiba.mobile.pruebadeingreso.repository;

import io.reactivex.Single;

public interface UserRepository {

    Single<String> getUserList();
}
