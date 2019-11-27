package com.app.didaktikapp.SopaLetras;

import android.app.Application;

import com.app.didaktikapp.SopaLetras.di.component.AppComponent;
//import com.app.didaktikapp.SopaLetras.di.component.DaggerAppComponent;
import com.app.didaktikapp.SopaLetras.di.modules.AppModule;

/**
 * Created by abdularis on 18/07/17.
 */

public class WordSearchApp extends Application {

    AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        //mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}
