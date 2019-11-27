package com.app.didaktikapp.SopaLetras.features;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.app.didaktikapp.R;
import com.app.didaktikapp.SopaLetras.WordSearchApp;
import com.app.didaktikapp.SopaLetras.features.mainmenu.MainMenuActivity;
import com.app.didaktikapp.SopaLetras.features.settings.Preferences;

import javax.inject.Inject;

/**
 * Created by abdularis on 21/04/17.
 *
 * Extend this class to make a fullscreen activity
 */

@SuppressLint("Registered")
public class FullscreenActivity extends AppCompatActivity {/*implementation 'com.jakewharton:butterknife:10.2.0'
      implementation 'com.google.dagger:dagger:2.25.2'
      implementation 'androidx.appcompat:appcompat:1.1.0'
      implementation 'androidx.recyclerview:recyclerview:1.1.0'
      implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
      implementation 'androidx.lifecycle:lifecycle-extensions:2.1.0'
      implementation 'androidx.lifecycle:lifecycle-viewmodel:2.1.0'
      implementation 'io.reactivex.rxjava2:rxjava:2.1.6'
      implementation 'io.reactivex.rxjava2:rxandroid:2.0.1'
      implementation 'com.github.abdularis:CircularImageView:v1.2'
      implementation 'com.jakewharton:butterknife:10.2.0'
      annotationProcessor 'com.jakewharton:butterknife-compiler:10.2.0'
      testImplementation 'junit:junit:4.12'
      annotationProcessor 'com.jakewharton:butterknife-compiler:10.2.0'
      annotationProcessor 'com.google.dagger:dagger-compiler:2.25.2'
      androidTestImplementation 'androidx.test.ext:junit:1.1.1'
      androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'*/

    @Inject
    Preferences mPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        ((WordSearchApp) getApplication()).getAppComponent().inject(this);
        if (mPreferences.enableFullscreen()) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    protected Preferences getPreferences() {
        return mPreferences;
    }
}
