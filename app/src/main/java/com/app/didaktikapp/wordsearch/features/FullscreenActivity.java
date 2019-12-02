package com.app.didaktikapp.wordsearch.features;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.Window;
import android.view.WindowManager;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.didaktikapp.R;
import com.app.didaktikapp.wordsearch.WordSearchApp;
import com.app.didaktikapp.wordsearch.features.settings.Preferences;

import javax.inject.Inject;

/**
 * Created by abdularis on 21/04/17.
 *
 * Extend this class to make a fullscreen activity
 */

@SuppressLint("Registered")
public class FullscreenActivity extends AppCompatActivity {

    @Inject
    Preferences mPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((WordSearchApp) getApplication()).getAppComponent().inject(this);

        if (mPreferences.enableFullscreen()) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    protected Preferences getPreferences() {
        return mPreferences;
    }
}
