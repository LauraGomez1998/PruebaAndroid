package co.com.ceiba.mobile.pruebadeingreso.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityPostBinding;
import co.com.ceiba.mobile.pruebadeingreso.viewmodel.PostActivityViewModel;
import co.com.ceiba.mobile.pruebadeingreso.viewmodel.handler.CallBackResponse;

import java.util.List;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.common.constant.ResponseStatus;
import co.com.ceiba.mobile.pruebadeingreso.model.Post;
import co.com.ceiba.mobile.pruebadeingreso.model.User;
import co.com.ceiba.mobile.pruebadeingreso.view.ui.adapter.PostListAdapter;
import dagger.android.AndroidInjection;

/**
 * Component ID: PostActivity.java <br>
 * Description: In this screen the post of a user selected are presented. <br>
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
public class PostActivity extends AppCompatActivity {

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;

    public static final String USER_ID = "USER_ID";

    private PostActivityViewModel viewModel;

    private ActivityPostBinding binding;

    private ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post);
        AndroidInjection.inject(this);

        //ViewModel
        viewModel = new ViewModelProvider(this, viewModelFactory).get(PostActivityViewModel.class);

        //Observers
        viewModel.getUserListResponse.observe(this, getUserPostsObserver);

        //Logic
        render();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    //region Observers
    private Observer<CallBackResponse> getUserPostsObserver = response -> {
        if (response != null) {
            switch (response.status) {
                case ResponseStatus.SUCCESS:
                    List<Post> userList = (List<Post>) response.data;

                    //Set adapter
                    RecyclerView.Adapter mAdapter = new PostListAdapter(this, userList);
                    binding.recyclerViewPostsResults.setAdapter(mAdapter);

                    break;
                case ResponseStatus.ERROR:
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.generic_error), Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            hideProgressDialog();
        }
    };
    //endregion


    //region Logic
    private void render() {
        Bundle bundle = getIntent().getExtras();
        User user = (User) bundle.getSerializable(USER_ID);

        binding.name.setText(user.getName());
        binding.phone.setText(user.getPhone());
        binding.email.setText(user.getEmail());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerViewPostsResults.setLayoutManager(layoutManager);

        showProgressDialog();
        viewModel.getUserPostsList(user.getId());
    }

    private void showProgressDialog() {
        progress = ProgressDialog.show(this, getResources().getString(R.string.generic_message_progress), null, true);
        progress.show();
    }

    private void hideProgressDialog() {
        progress.dismiss();
    }
    //endregion
}
