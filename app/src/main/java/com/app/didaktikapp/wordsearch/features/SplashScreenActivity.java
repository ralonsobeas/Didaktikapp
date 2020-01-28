package com.app.didaktikapp.wordsearch.features;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.didaktikapp.wordsearch.features.gameplay.GamePlayActivity;


public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Intent intent = new Intent(this, MainMenuActivity.class);
//        startActivity(intent);
        Intent intent = new Intent(SplashScreenActivity.this, GamePlayActivity.class);
        Bundle extras = getIntent().getExtras();
        intent.putExtra(GamePlayActivity.EXTRA_ROW_COUNT, 10);
        intent.putExtra(GamePlayActivity.EXTRA_COL_COUNT, 10);


        startActivity(intent);

        finish();
    }
}
