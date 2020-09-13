package com.example.presentation.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.presentation.R;
import com.example.presentation.viewmodel.MainActivityViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;

    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidInjection.inject(this);

        viewModel = new ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel.class);

        //Observers
        viewModel.getUserListResponse.observe(this, getListObserver);

        //Logic
        viewModel.getUserList();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private Observer<String> getListObserver = response -> {
        Log.d("prueba", response);
    };

}