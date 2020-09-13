package com.example.presentation.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.common.constant.ResponseStatus;
import co.com.ceiba.mobile.pruebadeingreso.model.User;
import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity implements UserSelectedClick {

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;

    private ActivityMainBinding binding;

    private List<User> userList;

    private UserListAdapter mAdapter;

    private ProgressDialog progress;


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
        showProgressDialog();
        viewModel.getUserList();
        binding.editTextSearch.addTextChangedListener(onTextChanged);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void userSelectedOnClickListener(User user) {
        Intent intent = new Intent(this, PostActivity.class);
        intent.putExtra(PostActivity.USER_ID, user);
        startActivity(intent);
    }


    //region Listeners
    private TextWatcher onTextChanged = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            filter(editable.toString());
        }
    };
    //endregion


    //region Observer
    private Observer<CallBackResponse> getListObserver = response -> {
        if (response != null) {
            switch (response.status) {
                case ResponseStatus.SUCCESS:
                    userList = (List<User>) response.data;

                    //Set adapter
                    mAdapter = new UserListAdapter(this, userList, this);
                    binding.recyclerViewSearchResults.setAdapter(mAdapter);

                    break;
                case ResponseStatus.ERROR:
                    binding.layoutEmptyView.setVisibility(View.VISIBLE);
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
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerViewSearchResults.setLayoutManager(layoutManager);
        userList = new ArrayList<>();
    }

    private void showProgressDialog() {
        progress = ProgressDialog.show(this, getResources().getString(R.string.generic_message_progress), null, true);
        progress.show();
    }

    private void hideProgressDialog() {
        progress.dismiss();
    }

    public void filter(String text) {
        List<User> filteredList = new ArrayList<>();
        for (User user : userList) {
            if (user.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(user);
            }
        }
        mAdapter.filterList(filteredList);
    }
    //endregion
}