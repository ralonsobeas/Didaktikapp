package com.app.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

public class SopaActivity extends AppCompatActivity {

    TextView[] textViewArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sopa);

        textViewArray = new TextView[100];

        TextView tv = findViewById(R.id.tv0_0);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tv.setBackgroundColor(getResources().getColor(R.color.colorAccent, null));
            }

        });


    }
}
