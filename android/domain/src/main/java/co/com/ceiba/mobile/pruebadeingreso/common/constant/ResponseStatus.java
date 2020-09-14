package co.com.ceiba.mobile.pruebadeingreso.common.constant;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Component ID: ResponseStatus.java <br>
 * Description: Possible status for asynchronous operations performed through ViewModel layer. <br>
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
@Retention(SOURCE)
public @interface ResponseStatus {

    String LOADING = "LOADING";

    String ERROR = "ERROR";

    String SUCCESS = "SUCCESS";
}
