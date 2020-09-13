package co.com.ceiba.mobile.pruebadeingreso.usecase;

import java.util.List;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.model.Post;
import co.com.ceiba.mobile.pruebadeingreso.model.User;
import co.com.ceiba.mobile.pruebadeingreso.repository.UserRepository;
import io.reactivex.Single;

public class UserUseCaseImpl implements UserUseCase {

    private UserRepository userRepository;

    @Inject
    public UserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Single<List<User>> getUserList() {
        return userRepository.getUserList();
    }

    @Override
    public Single<List<Post>> getUserPostsList(Integer userId) {
        return userRepository.getUserPostsList(userId);
    }
}
