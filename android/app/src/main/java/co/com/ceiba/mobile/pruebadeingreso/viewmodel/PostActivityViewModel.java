package co.com.ceiba.mobile.pruebadeingreso.viewmodel;

import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.common.constant.ResponseStatus;
import co.com.ceiba.mobile.pruebadeingreso.usecase.UserUseCase;
import co.com.ceiba.mobile.pruebadeingreso.viewmodel.handler.BaseViewModel;
import co.com.ceiba.mobile.pruebadeingreso.viewmodel.handler.CallBackResponse;

/**
 * Component ID: PostActivityViewModel.java <br>
 * Description: Controller for {@link co.com.ceiba.mobile.pruebadeingreso.view.PostActivity}. <br>
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
