package com.app.didaktikapp.SopaLetras.di.component;

import com.app.didaktikapp.SopaLetras.di.modules.AppModule;
import com.app.didaktikapp.SopaLetras.di.modules.DataSourceModule;
import com.app.didaktikapp.SopaLetras.features.FullscreenActivity;
//import com.app.didaktikapp.SopaLetras.features.gamehistory.GameHistoryActivity;
import com.app.didaktikapp.SopaLetras.features.gameover.GameOverActivity;
import com.app.didaktikapp.SopaLetras.features.gameplay.GamePlayActivity;
import com.app.didaktikapp.SopaLetras.features.mainmenu.MainMenuActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by abdularis on 18/07/17.
 */

@Singleton
@Component(modules = {AppModule.class, DataSourceModule.class})
public interface AppComponent {

    void inject(GamePlayActivity activity);

    void inject(MainMenuActivity activity);

    void inject(GameOverActivity activity);

    void inject(FullscreenActivity activity);

    //void inject(GameHistoryActivity activity);

}
