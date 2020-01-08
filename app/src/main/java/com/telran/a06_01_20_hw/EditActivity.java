package com.telran.a06_01_20_hw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.telran.a06_01_20_hw.databinding.ActivityEditBinding;

public class EditActivity extends AppCompatActivity /*implements IView*/{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StoreProvider store = new StoreProvider(this);
        ActivityEditBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_edit);
        EditViewModel viewModel = new EditViewModel(getIntent().getIntExtra("INDEX",-1),store);
        binding.setViewModel(viewModel);

        viewModel.getStateLiveData().observe(this,state->{
            Toast.makeText(this, state, Toast.LENGTH_SHORT).show();
            finish();
        });
    }

}
