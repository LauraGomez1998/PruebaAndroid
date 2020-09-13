/*
 * NOTICE: All information contained herein is, and remains the property of Brightinsight Inc. or
 * its customer. The intellectual and technical concepts contained herein are proprietary to
 * Brightinsight Inc. or its customer and may be covered by U.S. and Foreign Patents, patents in
 * process, and are protected by trade secret or copyright law.
 *
 * Dissemination of this information or reproduction of this material is strictly forbidden
 * unless prior written permission is obtained from Brightinsight Inc. or its customer.
 *
 * Access to the source code contained herein is hereby forbidden to anyone except current
 * Brightinsight Inc. employees, managers or contractors who have executed. Confidentiality and
 * Non-disclosure agreements explicitly covering such access.
 */
package co.com.ceiba.mobile.pruebadeingreso.remote;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.remote.model.post.GetUserPostsResp;
import co.com.ceiba.mobile.pruebadeingreso.remote.model.user.GetUsersResp;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Parameter Name: Tresiba Start <br>
 * Component ID: UserService.java <br>
 * Description: ReST API services signature definition for assessment-service of Tresiba Backend Service Cloud. <br>
 * Author: <a href="mailto:lagomez@heinsohn.com.co">Laura Gómez</a> <br>
 * Copyright ©: Brightinsight, Inc. <br>
 * <p/>
 * Revision Change
 * <table>
 * <tr>
 * <th>Author</th><th>Date</th><th>Version</th><th>Change-Description</th>
 * </tr>
 * <tr>
 * <td>Laura Gómez</td><td>2/13/2020</td><td>1.0</td><td>Initial</td>
 * </tr>
 * </table>
 */
public interface UserService {

    @GET("users")
    Single<List<GetUsersResp>> getUsersList();

    @GET("posts")
    Single<List<GetUserPostsResp>> getUserPostsList(@Query("userId") Integer userId);
}
