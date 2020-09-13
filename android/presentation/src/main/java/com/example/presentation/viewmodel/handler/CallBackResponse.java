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
package com.example.presentation.viewmodel.handler;

import co.com.ceiba.mobile.pruebadeingreso.common.constant.ResponseStatus;

/**
 * Parameter Name: Tresiba Start <br>
 * Component ID: TresibaResponse.java <br>
 * Description: Used as wrapper of a model find to catching the real status of background operations. <br>
 * Author: <a href="mailto:fvasquez@heinsohn.com.co">Ferney Vásquez</a> <br>
 * Copyright ©: Brightinsight, Inc. <br>
 * <p/>
 * Revision Change
 * <table>
 * <tr>
 * <th>Author</th><th>Date</th><th>Version</th><th>Change-Description</th>
 * </tr>
 * <tr>
 * <td>Ferney Vásquez</td><td>8/22/2019</td><td>1.0</td><td>Initial</td>
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