package co.com.ceiba.mobile.pruebadeingreso.viewmodel;

import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.common.constant.ResponseStatus;
import co.com.ceiba.mobile.pruebadeingreso.usecase.UserUseCase;
import co.com.ceiba.mobile.pruebadeingreso.viewmodel.handler.BaseViewModel;
import co.com.ceiba.mobile.pruebadeingreso.viewmodel.handler.CallBackResponse;

public class PostActivityViewModel extends BaseViewModel {

    private UserUseCase userUseCase;

    public MutableLiveData<CallBackResponse> getUserListResponse;

    @Inject
    public PostActivityViewModel(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
        getUserListResponse = new MutableLiveData<>();
    }

    public void getUserPostsList(Integer userId) {
        addSingle(userUseCase.getUserPostsList(userId),
                result -> {
                    getUserListResponse.setValue(CallBackResponse.success(result));
                },
                throwable -> {
                    throwable.printStackTrace();
                    getUserListResponse.setValue(CallBackResponse.error(ResponseStatus.ERROR));
                });
    }
}
