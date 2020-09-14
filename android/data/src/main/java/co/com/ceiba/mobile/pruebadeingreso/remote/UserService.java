package co.com.ceiba.mobile.pruebadeingreso.remote;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.remote.model.post.GetUserPostsResp;
import co.com.ceiba.mobile.pruebadeingreso.remote.model.user.GetUsersResp;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Component ID: UserService.java <br>
 * Description: ReST API services signature definition. <br>
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
public interface UserService {

    @GET("users")
    Single<List<GetUsersResp>> getUsersList();

    @GET("posts")
    Single<List<GetUserPostsResp>> getUserPostsList(@Query("userId") Integer userId);
}
