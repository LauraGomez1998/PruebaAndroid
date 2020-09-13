package com.example.presentation.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.usecase.UserUseCase;

public class MainActivityViewModel extends BaseViewModel {

    private UserUseCase userUseCase;

    public MutableLiveData<String> getUserListResponse;

    @Inject
    public MainActivityViewModel(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
        getUserListResponse = new MutableLiveData<>();
    }

    public void getUserList() {
        addSingle(userUseCase.getList(),
                result -> {
                    Log.d("prueba", result.toString());
                    getUserListResponse.setValue("SUCCESS");
                },
                throwable -> getUserListResponse.setValue("ERROR"));
    }
}
