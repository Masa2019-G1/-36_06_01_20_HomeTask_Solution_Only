package com.telran.a06_01_20_hw;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.telran.a06_01_20_hw.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private StoreProvider store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        store = new StoreProvider(this);

        LoginViewModel viewModel = new LoginViewModel(store);

        viewModel.getAuthStatusChanged().observe(this, status -> {
            if(status){
                showNextView();
            }
        });

        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setAuth(viewModel);

    }

    private void showNextView() {
        Intent intent = new Intent(this, ListActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK){
            finish();
        }
    }
}
