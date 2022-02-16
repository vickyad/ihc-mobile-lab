package com.example.myapplication;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    EditText n1;
    EditText n2;
    Button addButton;
    TextView result;
    int ans=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        n1=(EditText) findViewById(R.id.editText_n1);
        n2=(EditText) findViewById(R.id.editText_n2);
        addButton=(Button) findViewById(R.id.button);
        result = (TextView) findViewById(R.id.textView);

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                double num1 = Double.parseDouble(n1.getText().toString());
                double num2 = Double.parseDouble(n2.getText().toString());
                double sum = num1 + num2;

                result.setText(Double.toString(sum));
            }
        });
    }
}