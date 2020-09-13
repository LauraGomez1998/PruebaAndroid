package co.com.ceiba.mobile.pruebadeingreso.usecase;

import io.reactivex.Single;

public interface UserUseCase {

    Single<String> getList();
}
