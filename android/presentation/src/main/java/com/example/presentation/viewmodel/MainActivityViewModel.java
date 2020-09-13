package com.example.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.example.presentation.viewmodel.handler.BaseViewModel;
import com.example.presentation.viewmodel.handler.CallBackResponse;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.common.constant.ResponseStatus;
import co.com.ceiba.mobile.pruebadeingreso.usecase.UserUseCase;

public class MainActivityViewModel extends BaseViewModel {

    private UserUseCase userUseCase;

    public MutableLiveData<CallBackResponse> getUserListResponse;

    @Inject
    public MainActivityViewModel(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
        getUserListResponse = new MutableLiveData<>();
    }

    public void getUserList() {
        addSingle(userUseCase.getUserList(),
                result -> getUserListResponse.setValue(CallBackResponse.success(result)),
                throwable -> {
                    throwable.printStackTrace();
                    getUserListResponse.setValue(CallBackResponse.error(ResponseStatus.ERROR));
                });
    }
}
