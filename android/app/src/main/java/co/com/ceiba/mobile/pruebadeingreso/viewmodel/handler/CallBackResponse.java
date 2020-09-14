package co.com.ceiba.mobile.pruebadeingreso.viewmodel.handler;

import co.com.ceiba.mobile.pruebadeingreso.common.constant.ResponseStatus;

/**
 * Component ID: CallBackResponse.java <br>
 * Description: Used as wrapper of a model find to catching the real status of background operations. <br>
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
public class CallBackResponse {

    public final String status;

    public final Object data;

    public final String errorCode;


    private CallBackResponse(String status, Object data, String errorCode) {
        this.status = status;
        this.data = data;
        this.errorCode = errorCode;
    }

    public static CallBackResponse success(Object data) {
        return new CallBackResponse(ResponseStatus.SUCCESS, data, null);
    }

    public static CallBackResponse error(String errorCode) {
        return new CallBackResponse(ResponseStatus.ERROR, false, errorCode);
    }
}