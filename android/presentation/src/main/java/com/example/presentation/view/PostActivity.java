package com.example.presentation.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.presentation.R;
import com.example.presentation.databinding.ActivityPostBinding;
import com.example.presentation.view.ui.adapter.PostListAdapter;
import com.example.presentation.viewmodel.PostActivityViewModel;
import com.example.presentation.viewmodel.handler.CallBackResponse;

import java.util.List;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.common.constant.ResponseStatus;
import co.com.ceiba.mobile.pruebadeingreso.model.Post;
import co.com.ceiba.mobile.pruebadeingreso.model.User;
import dagger.android.AndroidInjection;

public class PostActivity extends AppCompatActivity {

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;

    public static final String USER_ID = "USER_ID";

    private ActivityPostBinding binding;

    private PostActivityViewModel viewModel;

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
                    Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }

    };


    private void render() {
        Bundle bundle = getIntent().getExtras();
        User user = (User) bundle.getSerializable(USER_ID);

        binding.name.setText(user.getName());
        binding.phone.setText(user.getPhone());
        binding.email.setText(user.getEmail());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerViewPostsResults.setLayoutManager(layoutManager);
        viewModel.getUserPostsList(user.getId());
    }
}
