package com.app.didaktikapp.wordsearch.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


import com.app.didaktikapp.wordsearch.data.GameDataSource;
import com.app.didaktikapp.wordsearch.data.WordDataSource;
import com.app.didaktikapp.wordsearch.features.ViewModelFactory;
import com.app.didaktikapp.wordsearch.features.gameover.GameOverViewModel;
import com.app.didaktikapp.wordsearch.features.gameplay.GamePlayViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

//import com.aar.app.wordsearch.features.gamehistory.GameHistoryViewModel;
//import com.aar.app.wordsearch.features.mainmenu.MainMenuViewModel;

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
                new GamePlayViewModel(gameDataSource, wordDataSource)
//                new MainMenuViewModel(new GameThemeRepository()),
//                new GameHistoryViewModel(gameDataSource)
        );
    }
}
