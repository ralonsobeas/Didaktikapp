package com.app.didaktikapp.SopaLetras.di.modules;

import android.content.Context;

import com.app.didaktikapp.SopaLetras.data.GameDataSource;
import com.app.didaktikapp.SopaLetras.data.WordDataSource;
import com.app.didaktikapp.SopaLetras.data.sqlite.DbHelper;
import com.app.didaktikapp.SopaLetras.data.sqlite.GameDataSQLiteDataSource;
import com.app.didaktikapp.SopaLetras.data.xml.WordXmlDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by abdularis on 18/07/17.
 */

@Module
public class DataSourceModule {

    @Provides
    @Singleton
    DbHelper provideDbHelper(Context context) {
        return new DbHelper(context);
    }

    @Provides
    @Singleton
    GameDataSource provideGameRoundDataSource(DbHelper dbHelper) {
        return new GameDataSQLiteDataSource(dbHelper);
    }

//    @Provides
//    @Singleton
//    WordDataSource provideWordDataSource(DbHelper dbHelper) {
//        return new WordSQLiteDataSource(dbHelper);
//    }

    @Provides
    @Singleton
    WordDataSource provideWordDataSource(Context context) {
        return new WordXmlDataSource(context);
    }

}
