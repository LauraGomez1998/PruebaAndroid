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
package com.example.presentation.viewmodel;

import androidx.lifecycle.ViewModel;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Parameter Name: Tresiba Start <br>
 * Component ID: BaseViewModel.java <br>
 * Description: Parent ViewModel superclass for all ViewModels. Important: Do not include business logic or use case calls in this class.<br>
 * Author: <a href="mailto:fvasquez@heinsohn.com.co">Ferney Vásquez</a> <br>
 * Copyright ©: Brightinsight, Inc. <br>
 * <p/>
 * Revision Change
 * <table>
 * <tr>
 * <th>Author</th><th>Date</th><th>Version</th><th>Change-Description</th>
 * </tr>
 * <tr>
 * <td>Ferney Vásquez</td><td>20/10/2019</td><td>1.0</td><td>Initial</td>
 * </tr>
 * </table>
 */
public class BaseViewModel extends ViewModel {

    private CompositeDisposable disposable = new CompositeDisposable();

    protected void addSingle(Single<?> single, Consumer<? super Object> success, Consumer<? super Throwable> error) {
        disposable.add(single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        success, error
                ));
    }

    protected void addCompletable(Completable completable, Action success, Consumer<? super Throwable> error) {
        disposable.add(completable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success, error));
    }

//    protected void addObservable(Callable callable, Consumer<? super Object> success, Consumer<? super Throwable> error) {
//        disposable.add(Single.fromCallable(()-> callable.call())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        success, error
//                ));
//    }

    @Override
    protected void onCleared() {
        super.onCleared();

        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
