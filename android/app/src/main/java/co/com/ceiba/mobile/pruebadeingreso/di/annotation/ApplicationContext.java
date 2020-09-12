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
package co.com.ceiba.mobile.pruebadeingreso.di.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Parameter Name: Tresiba Start <br>
 * Component ID: ApplicationContext.java <br>
 * Description: Defines a class key for accessing to Android application context in Dagger. <br>
 * Author: <a href="mailto:fvasquez@heinsohn.com.co">Ferney Vásquez</a> <br>
 * Copyright ©: Brightinsight, Inc. <br>
 * <p/>
 * Revision Change
 * <table>
 * <tr>
 * <th>Author</th><th>Date</th><th>Version</th><th>Change-Description</th>
 * </tr>
 * <tr>
 * <td>Ferney Vásquez</td><td>4/10/2019</td><td>1.0</td><td>Initial</td>
 * </tr>
 * </table>
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationContext {
}
