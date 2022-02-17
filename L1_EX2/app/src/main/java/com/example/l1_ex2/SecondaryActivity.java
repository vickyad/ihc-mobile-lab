package com.example.l1_ex2;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SecondaryActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        textView = findViewById(R.id.textView);

        String message = getIntent().getStringExtra("message");
        textView.setText(message);
    }
}