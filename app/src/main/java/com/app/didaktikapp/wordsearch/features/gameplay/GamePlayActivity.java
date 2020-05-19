package com.app.didaktikapp.wordsearch.features.gameplay;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;

import com.app.didaktikapp.BBDD.Modelos.ActividadZumeltzegi;
import com.app.didaktikapp.BBDD.database.DatabaseRepository;
import com.app.didaktikapp.FTP.ClassToFtp;
import com.app.didaktikapp.R;
import com.app.didaktikapp.wordsearch.WordSearchApp;
import com.app.didaktikapp.wordsearch.commons.DurationFormatter;
import com.app.didaktikapp.wordsearch.commons.Util;
import com.app.didaktikapp.wordsearch.custom.LetterBoard;
import com.app.didaktikapp.wordsearch.custom.StreakView;
import com.app.didaktikapp.wordsearch.custom.layout.FlowLayout;
import com.app.didaktikapp.wordsearch.features.FullscreenActivity;
import com.app.didaktikapp.wordsearch.features.ViewModelFactory;
import com.app.didaktikapp.wordsearch.features.gameover.GameOverActivity;
import com.app.didaktikapp.wordsearch.model.GameData;
import com.app.didaktikapp.wordsearch.model.UsedWord;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

//import com.aar.app.wordsearch.features.SoundPlayer;

public class GamePlayActivity extends FullscreenActivity {

    public static final String EXTRA_GAME_ROUND_ID =
            "com.app.didaktikapp.wordsearch.features.gameplay.GamePlayActivity.ID";
    public static final String EXTRA_ROW_COUNT =
            "com.app.didaktikapp.wordsearch.features.gameplay.GamePlayActivity.ROW";
    public static final String EXTRA_COL_COUNT =
            "com.app.didaktikapp.wordsearch.features.gameplay.GamePlayActivity.COL";

    private static final StreakLineMapper STREAK_LINE_MAPPER = new StreakLineMapper();

    //variable que referencia el nombre del fragment para la base de datos y sus dos posibles opciones
    public static final String  NOMBRE_FRAGMENT = "nombre_fragment";
    public static final String  FRAGMENT_ZUMELTZEGI = "zumeltzegui";
    public static final String  FRAGMENT_ERREPASO2 = "errepaso2";

    //se cargara en funcion del nombre
    public static  String  ruta_sopa;
    public static final String SOPA_ZUMELTZEGI = "words_zumeltzegi.xml";
    public static final String SOPA_ERREPASO2 = "words_errepaso2.xml";



    @Inject
    ViewModelFactory mViewModelFactory;
    private GamePlayViewModel mViewModel;

    @BindView(R.id.text_duration)
    TextView mTextDuration;
    @BindView(R.id.letter_board)
    LetterBoard mLetterBoard;
    @BindView(R.id.flow_layout)
    FlowLayout mFlowLayout;

    @BindView(R.id.text_sel_layout)
    View mTextSelLayout;
    @BindView(R.id.text_selection)
    TextView mTextSelection;

    @BindView(R.id.finished_text)
    TextView mFinishedText;

    @BindView(R.id.loading)
    View mLoading;
    @BindView(R.id.loadingText)
    TextView mLoadingText;
    @BindView(R.id.content_layout)
    View mContentLayout;

    @BindColor(R.color.gray) int mGrayColor;

    private ArrayLetterGridDataAdapter mLetterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        ButterKnife.bind(this);
        ((WordSearchApp) getApplication()).getAppComponent().inject(this);

        mLetterBoard.getStreakView().setEnableOverrideStreakLineColor(getPreferences().grayscale());
        mLetterBoard.getStreakView().setOverrideStreakLineColor(mGrayColor);
        mLetterBoard.setOnLetterSelectionListener(new LetterBoard.OnLetterSelectionListener() {
            @Override
            public void onSelectionBegin(StreakView.StreakLine streakLine, String str) {
                streakLine.setColor(Util.getRandomColorWithAlpha(170));
                mTextSelLayout.setVisibility(View.VISIBLE);
                mTextSelection.setText(str);
            }

            @Override
            public void onSelectionDrag(StreakView.StreakLine streakLine, String str) {
                if (str.isEmpty()) {
                    mTextSelection.setText("...");
                } else {
                    mTextSelection.setText(str);
                }
            }

            @Override
            public void onSelectionEnd(StreakView.StreakLine streakLine, String str) {
                mViewModel.answerWord(str, STREAK_LINE_MAPPER.revMap(streakLine), getPreferences().reverseMatching());
                mTextSelLayout.setVisibility(View.GONE);
                mTextSelection.setText(str);
            }
        });

        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(GamePlayViewModel.class);
        mViewModel.getOnTimer().observe(this, this::showDuration);
        mViewModel.getOnGameState().observe(this, this::onGameStateChanged);
        mViewModel.getOnAnswerResult().observe(this, this::onAnswerResult);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            //seleccionar una sopa u otra en funcion del fragment
            String nombre = extras.getString(NOMBRE_FRAGMENT);
            if(nombre.equals(FRAGMENT_ZUMELTZEGI)){
                ruta_sopa = SOPA_ZUMELTZEGI;
            }else if(nombre.equals(FRAGMENT_ERREPASO2)){
                ruta_sopa = SOPA_ERREPASO2;
            }

            if (extras.containsKey(EXTRA_GAME_ROUND_ID)) {
                int gid = extras.getInt(EXTRA_GAME_ROUND_ID);
                mViewModel.loadGameRound(gid);
            } else {
                int rowCount = extras.getInt(EXTRA_ROW_COUNT);
                int colCount = extras.getInt(EXTRA_COL_COUNT);
                mViewModel.generateNewGameRound(rowCount, colCount, ruta_sopa);
            }
        }

        if (!getPreferences().showGridLine()) {
            mLetterBoard.getGridLineBackground().setVisibility(View.INVISIBLE);
        } else {
            mLetterBoard.getGridLineBackground().setVisibility(View.VISIBLE);
        }

        mLetterBoard.getStreakView().setSnapToGrid(getPreferences().getSnapToGrid());
        mFinishedText.setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.resumeGame();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewModel.pauseGame();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.stopGame();
    }

    private void onAnswerResult(GamePlayViewModel.AnswerResult answerResult) {
        if (answerResult.correct) {
            TextView textView = findUsedWordTextViewByUsedWordId(answerResult.usedWordId);
            if (textView != null) {
                UsedWord uw = (UsedWord) textView.getTag();

                if (getPreferences().grayscale()) {
                    uw.getAnswerLine().color = mGrayColor;
                }
                textView.setBackgroundColor(uw.getAnswerLine().color);
                textView.setText(uw.getString());
                textView.setTextColor(Color.WHITE);
                textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                Animator anim = AnimatorInflater.loadAnimator(this, R.animator.zoom_in_out);
                anim.setTarget(textView);
                anim.start();
            }

        }
        else {
            mLetterBoard.popStreakLine();

        }
    }

    private void onGameStateChanged(GamePlayViewModel.GameState gameState) {
        showLoading(false, null);
        if (gameState instanceof GamePlayViewModel.Generating) {
            GamePlayViewModel.Generating state = (GamePlayViewModel.Generating) gameState;
            String text = "Generating " + state.rowCount + "x" + state.colCount + " grid";
            showLoading(true, text);
        } else if (gameState instanceof GamePlayViewModel.Finished) {
            showFinishGame(((GamePlayViewModel.Finished) gameState).mGameData.getId());
        } else if (gameState instanceof GamePlayViewModel.Paused) {

        } else if (gameState instanceof GamePlayViewModel.Playing) {
            onGameRoundLoaded(((GamePlayViewModel.Playing) gameState).mGameData);
        }
    }

    private void onGameRoundLoaded(GameData gameData) {
        if (gameData.isFinished()) {
            setGameAsAlreadyFinished();
        }

        showLetterGrid(gameData.getGrid().getArray());
        showDuration(gameData.getDuration());
        showUsedWords(gameData.getUsedWords());
        doneLoadingContent();
    }

    private void tryScale() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int boardWidth = mLetterBoard.getWidth();
        int screenWidth = metrics.widthPixels;

        if (getPreferences().autoScaleGrid() || boardWidth > screenWidth) {
            float scale = (float)screenWidth / (float)boardWidth;
            mLetterBoard.scale(scale, scale);
        }
    }

    private void doneLoadingContent() {
        // call tryScale() on the next render frame
        new Handler().postDelayed(this::tryScale, 100);
    }

    private void showLoading(boolean enable, String text) {
        if (enable) {
            mLoading.setVisibility(View.VISIBLE);
            mLoadingText.setVisibility(View.VISIBLE);
            mContentLayout.setVisibility(View.GONE);
            mLoadingText.setText(text);
        } else {
            mLoading.setVisibility(View.GONE);
            mLoadingText.setVisibility(View.GONE);
            mContentLayout.setVisibility(View.VISIBLE);
        }
    }

    private void showLetterGrid(char[][] grid) {
        if (mLetterAdapter == null) {
            mLetterAdapter = new ArrayLetterGridDataAdapter(grid);
            mLetterBoard.setDataAdapter(mLetterAdapter);
        }
        else {
            mLetterAdapter.setGrid(grid);
        }
    }

    private void showDuration(int duration) {
        mTextDuration.setText(DurationFormatter.fromInteger(duration));
    }

    private void showUsedWords(List<UsedWord> usedWords) {
        for (UsedWord uw : usedWords) {
            mFlowLayout.addView( createUsedWordTextView(uw) );
        }
    }

    private void showFinishGame(int gameId) {
        vibrar();
        Intent intent = new Intent(this, GameOverActivity.class);
        intent.putExtra(GameOverActivity.EXTRA_GAME_ROUND_ID, gameId);


        //seleccionar una sopa u otra en funcion del fragment
        Bundle extras = getIntent().getExtras();
        String nombre = extras.getString(NOMBRE_FRAGMENT);
        if(nombre.equals(FRAGMENT_ZUMELTZEGI)){

//            DatabaseRepository.getAppDatabase().getZumeltzegiDao().getZumeltzegi(DatabaseRepository.getAppDatabase().getGrupoDao().getGrupo(MapActivity.idgrupo)).setSopa(mTextDuration);
            ActividadZumeltzegi actividadZumeltzegi = DatabaseRepository.getAppDatabase().getZumeltzegiDao().getZumeltzegi(new Long(1));
            actividadZumeltzegi.setSopa(mTextDuration.getText().toString());
            actividadZumeltzegi.setFragment(2);
            actividadZumeltzegi.setEstado(2);

            DatabaseRepository.getAppDatabase().getZumeltzegiDao().updateZumeltzegi(actividadZumeltzegi);
            ClassToFtp.send(getBaseContext(),ClassToFtp.TIPO_ZUMELTZEGI);


        }else if(nombre.equals(FRAGMENT_ERREPASO2)){
            String a = "hola";
            Log.i("tag","repaso");
        }


        startActivity(intent);
        finish();
    }

    private void vibrar(){
        // Get instance of Vibrator from current Context
        Vibrator v = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);

        // Vibrate for 400 milliseconds
        v.vibrate(400);
    }

    private void setGameAsAlreadyFinished() {
        mLetterBoard.getStreakView().setInteractive(false);
        mFinishedText.setVisibility(View.VISIBLE);
    }

    //
    private TextView createUsedWordTextView(UsedWord uw) {
        TextView tv = new TextView(this);
        tv.setPadding(10, 5, 10, 5);
        if (uw.isAnswered()) {
            if (getPreferences().grayscale()) {
                uw.getAnswerLine().color = mGrayColor;
            }
            tv.setBackgroundColor(uw.getAnswerLine().color);
            tv.setText(uw.getString());
            tv.setTextColor(Color.WHITE);
            tv.setPaintFlags(tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            mLetterBoard.addStreakLine(STREAK_LINE_MAPPER.map(uw.getAnswerLine()));
        }
        else {
            String str = uw.getString();
            tv.setText(str);
            tv.setTextSize(20.0f);
        }

        tv.setTag(uw);
        return tv;
    }

    private TextView findUsedWordTextViewByUsedWordId(int usedWordId) {
        for (int i = 0; i < mFlowLayout.getChildCount(); i++) {
            TextView tv = (TextView) mFlowLayout.getChildAt(i);
            UsedWord uw = (UsedWord) tv.getTag();
            if (uw != null && uw.getId() == usedWordId) {
                return tv;
            }
        }

        return null;
    }
}
