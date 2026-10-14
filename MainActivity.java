package com.huanling.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import bsh.Interpreter;

public class MainActivity extends Activity {

    private EditText etCode;
    private TextView tvOutput;
    private Button btnRun;
    private Button btnClear;
    private Handler handler = new Handler(Looper.getMainLooper());
    private boolean announced = false;

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

        setContentView(R.layout.activity_main);

        etCode = findViewById(R.id.et_code);
        tvOutput = findViewById(R.id.tv_output);
        btnRun = findViewById(R.id.btn_run);
        btnClear = findViewById(R.id.btn_clear);

        etCode.setText("int a = 10;\nint b = 20;\nSystem.out.println(\"结果: \" + (a + b));");

        if (!announced) {
            announced = true;
            new announcementdialog(this).show();
        }

        btnRun.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					runCode();
				}
			});

        btnClear.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					tvOutput.setText("");
				}
			});
    }

    private void runCode() {
        final String code = etCode.getText().toString().trim();
        if (code.isEmpty()) {
            tvOutput.setText("// 请输入代码");
            return;
        }

        tvOutput.setText("// 运行中...");

        new Thread(new Runnable() {
				@Override
				public void run() {
					final ByteArrayOutputStream out = new ByteArrayOutputStream();
					final ByteArrayOutputStream err = new ByteArrayOutputStream();
					PrintStream oldOut = System.out;
					PrintStream oldErr = System.err;

					try {
						System.setOut(new PrintStream(out));
						System.setErr(new PrintStream(err));

						Interpreter interpreter = new Interpreter();
						interpreter.eval(code);

						final String result = out.toString() + err.toString();
						handler.post(new Runnable() {
								@Override
								public void run() {
									tvOutput.setText(result.isEmpty() ? "// 运行完成，无输出" : result);
								}
							});

					} catch (final Exception e) {
						handler.post(new Runnable() {
								@Override
								public void run() {
									tvOutput.setText("错误: " + e.getMessage());
								}
							});
					} finally {
						System.setOut(oldOut);
						System.setErr(oldErr);
					}
				}
			}).start();
    }
}
