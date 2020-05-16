package com.app.didaktikapp.Video;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.MediaController;

/**
 * Clase que maneja la reproduccion de videos
 * @author gennakk
 */
public class CustomMediaController extends MediaController {
    private Context context;
    public CustomMediaController(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public CustomMediaController(Context context, boolean useFastForward) {
        super(context, useFastForward);
        this.context = context;
    }

    public CustomMediaController(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void setAnchorView(View view) {
        super.setAnchorView(view);

        Button fullScreen = new Button(context);
        fullScreen.setText("FullScreen");
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.gravity =  Gravity.RIGHT|Gravity.TOP;
        addView(fullScreen, params);
    }
}
