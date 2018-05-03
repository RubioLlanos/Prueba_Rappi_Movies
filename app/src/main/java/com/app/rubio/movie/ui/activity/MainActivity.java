package com.app.rubio.movie.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.app.rubio.movie.R;
import com.app.rubio.movie.databinding.ActivityMainBinding;
import com.app.rubio.movie.viewModel.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initViewModel();
    }

    private void initViewModel() {
        binding.setViewModel(new MainViewModel());
    }
}
