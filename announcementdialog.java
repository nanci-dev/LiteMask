package com.huanling.app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class announcementdialog extends Dialog {

    public announcementdialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_announcement);

        // 去掉系统默认白色背景
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        // 弹窗进出动画
        getWindow().setWindowAnimations(R.style.DialogAnim);

        Button btnConfirm = findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					dismiss();
				}
			});
    }
}
