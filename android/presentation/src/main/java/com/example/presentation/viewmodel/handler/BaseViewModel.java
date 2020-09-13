package com.example.presentation.viewmodel.handler;

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


    @Override
    protected void onCleared() {
        super.onCleared();

        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
