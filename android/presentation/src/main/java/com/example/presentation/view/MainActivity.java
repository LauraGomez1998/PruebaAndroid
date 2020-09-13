package com.example.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.presentation.R;
import com.example.presentation.databinding.ActivityMainBinding;
import com.example.presentation.view.ui.adapter.UserListAdapter;
import com.example.presentation.view.ui.adapter.UserSelectedClick;
import com.example.presentation.viewmodel.MainActivityViewModel;
import com.example.presentation.viewmodel.handler.CallBackResponse;

import java.util.List;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.common.constant.ResponseStatus;
import co.com.ceiba.mobile.pruebadeingreso.model.User;
import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity implements UserSelectedClick {

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        AndroidInjection.inject(this);

        //ViewModel
        MainActivityViewModel viewModel = new ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel.class);

        //Observers
        viewModel.getUserListResponse.observe(this, getListObserver);

        //Logic
        render();
        viewModel.getUserList();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private Observer<CallBackResponse> getListObserver = response -> {
        if (response != null) {
            switch (response.status) {
                case ResponseStatus.SUCCESS:
                    List<User> userList = (List<User>) response.data;

                    //Set adapter
                    RecyclerView.Adapter mAdapter = new UserListAdapter(this, userList, this);
                    binding.recyclerViewSearchResults.setAdapter(mAdapter);

                    break;
                case ResponseStatus.ERROR:
                    binding.layoutEmptyView.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    private void render() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerViewSearchResults.setLayoutManager(layoutManager);
    }

    @Override
    public void userSelectedOnClickListener(User user) {
        Intent intent = new Intent(this, PostActivity.class);
        intent.putExtra(PostActivity.USER_ID, user);
        startActivity(intent);
    }
}