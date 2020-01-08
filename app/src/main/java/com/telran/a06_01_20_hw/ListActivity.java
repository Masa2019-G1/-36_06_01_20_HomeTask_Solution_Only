package com.telran.a06_01_20_hw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.telran.a06_01_20_hw.databinding.ActivityListBinding;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private ContactAdapter adapter;
    private ActivityListBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StoreProvider provider = new StoreProvider(this);
        ListViewModel viewModel = new ListViewModel(provider);
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
                    intent.putExtra("INDEX",-1);
                    startActivity(intent);
                    break;
            }
        });


        binding.contactList.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this,EditActivity.class);
            intent.putExtra("INDEX",position);
            startActivity(intent);
        });

    }

//    @Override
//    protected void onRestart() {
//        binding.getViewModel().update();
//        super.onRestart();
//    }
}
