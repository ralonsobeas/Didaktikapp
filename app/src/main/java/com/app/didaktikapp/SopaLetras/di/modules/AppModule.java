package com.app.didaktikapp.SopaLetras.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.app.didaktikapp.SopaLetras.data.GameDataSource;
import com.app.didaktikapp.SopaLetras.data.GameThemeRepository;
import com.app.didaktikapp.SopaLetras.data.WordDataSource;
import com.app.didaktikapp.SopaLetras.features.ViewModelFactory;
//import com.app.didaktikapp.SopaLetras.features.gamehistory.GameHistoryViewModel;
import com.app.didaktikapp.SopaLetras.features.gameover.GameOverViewModel;
import com.app.didaktikapp.SopaLetras.features.gameplay.GamePlayViewModel;
import com.app.didaktikapp.SopaLetras.features.mainmenu.MainMenuViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by abdularis on 18/07/17.
 */

@Module
public class AppModule {

    private Application mApp;

    public AppModule(Application application) {
        mApp = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mApp;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    ViewModelFactory provideViewModelFactory(GameDataSource gameDataSource,
                                             WordDataSource wordDataSource) {
        return new ViewModelFactory(
                new GameOverViewModel(gameDataSource),
                new GamePlayViewModel(gameDataSource, wordDataSource),
                new MainMenuViewModel(new GameThemeRepository())
                //new GameHistoryViewModel(gameDataSource)
        );
    }
}
