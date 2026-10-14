package com.huanling.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

public class splashactivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 真全屏：隐藏状态栏和底部导航栏
        getWindow().setFlags(
			WindowManager.LayoutParams.FLAG_FULLSCREEN,
			WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        getWindow().getDecorView().setSystemUiVisibility(
			View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
			| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
			| View.SYSTEM_UI_FLAG_FULLSCREEN
			| View.SYSTEM_UI_FLAG_LAYOUT_STABLE
			| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
			| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        );

        setContentView(R.layout.activity_splash);

        final TextView tvTitle = findViewById(R.id.tv_title);
        final TextView tvSubtitle = findViewById(R.id.tv_subtitle);
        final TextView btnEnter = findViewById(R.id.btn_enter);

        tvTitle.animate().alpha(1f).translationY(0f)
			.setDuration(800).setStartDelay(200)
			.setInterpolator(new DecelerateInterpolator()).start();

        tvSubtitle.animate().alpha(1f).translationY(0f)
			.setDuration(800).setStartDelay(500)
			.setInterpolator(new DecelerateInterpolator()).start();

        btnEnter.animate().alpha(1f).translationY(0f).scaleX(1f).scaleY(1f)
			.setDuration(800).setStartDelay(800)
			.setInterpolator(new DecelerateInterpolator()).start();

        btnEnter.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(final View v) {
					v.animate().scaleX(0.92f).scaleY(0.92f).setDuration(100)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                v.animate().scaleX(1f).scaleY(1f).setDuration(100).start();
                                startActivity(new Intent(v.getContext(), MainActivity.class));
                                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            }
                        }).start();
				}
			});
    }
}
