package com.telran.a06_01_20_hw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListAdapter;

import com.telran.a06_01_20_hw.databinding.ActivityListBinding;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private ContactAdapter adapter;
    private ActivityListBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListViewModel viewModel = new ListViewModel(new StoreProvider(this));
        binding = DataBindingUtil.setContentView(this,R.layout.activity_list);
        binding.setViewModel(viewModel);

        viewModel.getListLiveData().observe(this, list ->{
            adapter = new ContactAdapter(list);
            binding.contactList.setAdapter(adapter);
        });

        viewModel.getStateLiveData().observe(this, state ->{
            switch (state){
                case "LOGOUT":
                    setResult(RESULT_OK);
                    finish();
                    break;
                case "ADD":
                    Intent intent = new Intent(this,EditActivity.class);
                    startActivity(intent);
                    break;
            }
        });

    }

    @Override
    protected void onRestart() {
        binding.getViewModel().update();
        super.onRestart();
    }
}
