package com.telran.a06_01_20_hw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Button btn = findViewById(R.id.saveBtn);
        StoreProvider provider = new StoreProvider(this);
        btn.setOnClickListener(v -> {
            provider.add(new Contact("Vasya","email","phone","address"));
            finish();
        });
    }
}
