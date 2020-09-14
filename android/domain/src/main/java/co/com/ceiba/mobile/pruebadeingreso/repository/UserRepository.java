package co.com.ceiba.mobile.pruebadeingreso.repository;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.model.Post;
import co.com.ceiba.mobile.pruebadeingreso.model.User;
import io.reactivex.Single;

/**
 * Component ID: UserRepository.java <br>
 * Description: Interface to abstract the methods of UserRepositoryImpl.<br>
 * Author: <a href="mailto:lauragomez.lg247@gmail.com">Laura Gómez</a>
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
public interface UserRepository {

    Single<List<User>> getUserList();

    Single<List<Post>> getUserPostsList(Integer userId);

    List<User> getLocalUserList();
}
