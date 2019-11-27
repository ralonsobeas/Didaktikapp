package com.app.didaktikapp.SopaLetras.features.mainmenu;

import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.didaktikapp.R;
import com.app.didaktikapp.SopaLetras.WordSearchApp;
import com.app.didaktikapp.SopaLetras.easyadapter.MultiTypeAdapter;
import com.app.didaktikapp.SopaLetras.easyadapter.SimpleAdapterDelegate;
import com.app.didaktikapp.SopaLetras.features.FullscreenActivity;
import com.app.didaktikapp.SopaLetras.features.ViewModelFactory;
//import com.app.didaktikapp.SopaLetras.features.gamehistory.GameHistoryActivity;
import com.app.didaktikapp.SopaLetras.features.gameplay.GamePlayActivity;
import com.app.didaktikapp.SopaLetras.features.settings.SettingsActivity;
import com.app.didaktikapp.SopaLetras.model.GameTheme;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainMenuActivity extends FullscreenActivity {

    @BindView(R.id.rv) RecyclerView mRv;
    @BindView(R.id.game_template_spinner)
    Spinner mGridSizeSpinner;

    @BindArray(R.array.game_round_dimension_values)
    int[] mGameRoundDimVals;

    @Inject
    ViewModelFactory mViewModelFactory;
    MainMenuViewModel mViewModel;
    MultiTypeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sopa_activity_main_menu);
        Log.i("tag","mma");
        ButterKnife.bind(this);


        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainMenuViewModel.class);
        mViewModel.getOnGameThemeLoaded().observe(this, this::showGameThemeList);


        mAdapter = new MultiTypeAdapter();
        mAdapter.addDelegate(
                GameTheme.class,
                R.layout.sopa_item_game_theme,
                new SimpleAdapterDelegate.Binder<GameTheme>() {
                    @Override
                    public void bind(GameTheme model, SimpleAdapterDelegate.ViewHolder holder) {
                        holder.<TextView>find(R.id.textThemeName).setText(model.getName());
                    }
                },
                new SimpleAdapterDelegate.OnItemClickListener<GameTheme>() {
                    @Override
                    public void onClick(GameTheme model, View view) {
                        Toast.makeText(MainMenuActivity.this, model.getName(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
//        mAdapter.addDelegate(
//                HistoryItem.class,
//                R.layout.item_histories,
//                new SimpleAdapterDelegate.Binder<HistoryItem>() {
//                    @Override
//                    public void bind(HistoryItem model, SimpleAdapterDelegate.ViewHolder holder) {
//                    }
//                },
//                (model, view) -> {
//                    Intent i = new Intent(MainMenuActivity.this, GameHistoryActivity.class);
//                    startActivity(i);
//                }
//        );

        mRv.setAdapter(mAdapter);
        mRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mViewModel.loadData();
        ((WordSearchApp) getApplication()).getAppComponent().inject(this);

    }

    public void showGameThemeList(List<GameTheme> gameThemes) {
        mAdapter.setItems(gameThemes);
        mAdapter.insertAt(0, new HistoryItem());
    }

    @OnClick(R.id.settings_button)
    public void onSettingsClick() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.new_game_btn)
    public void onNewGameClick() {
        int dim = mGameRoundDimVals[ mGridSizeSpinner.getSelectedItemPosition() ];
        Intent intent = new Intent(MainMenuActivity.this, GamePlayActivity.class);
        intent.putExtra(GamePlayActivity.EXTRA_ROW_COUNT, dim);
        intent.putExtra(GamePlayActivity.EXTRA_COL_COUNT, dim);
        startActivity(intent);
    }

}
