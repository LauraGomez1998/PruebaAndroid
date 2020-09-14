package co.com.ceiba.mobile.pruebadeingreso.usecase;

import java.util.List;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.model.Post;
import co.com.ceiba.mobile.pruebadeingreso.model.User;
import co.com.ceiba.mobile.pruebadeingreso.repository.UserRepository;
import io.reactivex.Single;

/**
 * Component ID: UserRepository.java <br>
 * Description: Implementation for the user use case business logic.<br>
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
