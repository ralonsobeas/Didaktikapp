package com.app.didaktikapp.wordsearch.di.component;


import com.app.didaktikapp.wordsearch.di.modules.AppModule;
import com.app.didaktikapp.wordsearch.di.modules.DataSourceModule;
import com.app.didaktikapp.wordsearch.features.FullscreenActivity;
import com.app.didaktikapp.wordsearch.features.gameover.GameOverActivity;
import com.app.didaktikapp.wordsearch.features.gameplay.GamePlayActivity;

import javax.inject.Singleton;

import dagger.Component;

//import com.aar.app.wordsearch.features.gamehistory.GameHistoryActivity;
//import com.aar.app.wordsearch.features.mainmenu.MainMenuActivity;

/**
 * Created by abdularis on 18/07/17.
 */

@Singleton
@Component(modules = {AppModule.class, DataSourceModule.class})
public interface AppComponent {

    void inject(GamePlayActivity activity);

//    void inject(MainMenuActivity activity);

    void inject(GameOverActivity activity);

    void inject(FullscreenActivity activity);

//    void inject(GameHistoryActivity activity);

}
