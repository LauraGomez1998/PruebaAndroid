package co.com.ceiba.mobile.pruebadeingreso.usecase;

import com.google.common.base.Strings;

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
        List<User> userList = userRepository.getLocalUserList();

        if (!userList.isEmpty()) {
            return Single.just(userList);
        }
        return userRepository.getUserList();
    }

    @Override
    public Single<List<Post>> getUserPostsList(Integer userId) {
        return userRepository.getUserPostsList(userId);
    }
}
