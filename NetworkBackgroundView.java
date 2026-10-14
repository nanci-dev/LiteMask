package com.huanling.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class NetworkBackgroundView extends View {

    private Paint linePaint;
    private Paint dotPaint;
    private float[] xPos, yPos;
    private float[] xSpeed, ySpeed;
    private int pointCount = 50;
    private Random random = new Random();

    public NetworkBackgroundView(Context context) {
        super(context);
        init();
    }

    public NetworkBackgroundView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        linePaint = new Paint();
        linePaint.setColor(Color.parseColor("#33FFFFFF"));
        linePaint.setStrokeWidth(2f);
        linePaint.setAntiAlias(true);

        dotPaint = new Paint();
        dotPaint.setColor(Color.parseColor("#88FFFFFF"));
        dotPaint.setAntiAlias(true);

        xPos = new float[pointCount];
        yPos = new float[pointCount];
        xSpeed = new float[pointCount];
        ySpeed = new float[pointCount];
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        for (int i = 0; i < pointCount; i++) {
            xPos[i] = random.nextFloat() * w;
            yPos[i] = random.nextFloat() * h;
            xSpeed[i] = (random.nextFloat() - 0.5f) * 2f;
            ySpeed[i] = (random.nextFloat() - 0.5f) * 2f;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int w = getWidth();
        int h = getHeight();

        for (int i = 0; i < pointCount; i++) {
            xPos[i] += xSpeed[i];
            yPos[i] += ySpeed[i];
            if (xPos[i] < 0 || xPos[i] > w) xSpeed[i] *= -1;
            if (yPos[i] < 0 || yPos[i] > h) ySpeed[i] *= -1;
        }

        for (int i = 0; i < pointCount; i++) {
            for (int j = i + 1; j < pointCount; j++) {
                float dx = xPos[i] - xPos[j];
                float dy = yPos[i] - yPos[j];
                float dist = (float) Math.sqrt(dx * dx + dy * dy);
                if (dist < 300) {
                    int alpha = (int) (255 * (1 - dist / 300f) * 0.3);
                    linePaint.setAlpha(alpha);
                    canvas.drawLine(xPos[i], yPos[i], xPos[j], yPos[j], linePaint);
                }
            }
        }

        for (int i = 0; i < pointCount; i++) {
            canvas.drawCircle(xPos[i], yPos[i], 4f, dotPaint);
        }

        invalidate();
    }
}
