package com.ly.loading1.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class LoadingView extends View {

    private Paint paint1;
    private Paint paint2;
    private Path circlePath;
    private Path path;
    private PathMeasure pathMeasure;

    private float circleC;
    private float start;
    private float end;
    private boolean isChangeEnd = false;
    private float nowDegree;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context,AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        paint1 = new Paint();
        paint1.setColor(Color.BLUE);
        paint1.setAntiAlias(true);
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setStrokeWidth(50);

        paint2 = new Paint();
        paint2.setColor(Color.BLUE);
        paint2.setAntiAlias(true);

        circlePath = new Path();
        path = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        circlePath.reset();
        int height = getDefaultSize(0, heightMeasureSpec);
        int width = getDefaultSize(0, widthMeasureSpec);
        if(width > height){
            circlePath.addCircle(width / 2, height / 2, height / 2 - 50, Path.Direction.CW);
        }else{
            circlePath.addCircle(width / 2, height / 2, width / 2 - 50, Path.Direction.CW);
        }
        pathMeasure = new PathMeasure(circlePath, true);
        circleC = pathMeasure.getLength();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();
        pathMeasure.getSegment(end, start, path, true);

        canvas.drawPath(path, paint1);

        float[] pos = new float[2];
        float[] tan = new float[2];
        pathMeasure.getPosTan(end, pos, tan);
        canvas.drawCircle(pos[0], pos[1], 25, paint2);

        pathMeasure.getPosTan(start, pos, tan);
        canvas.drawCircle(pos[0], pos[1], 25, paint2);
    }

    public void startAnim(){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(2000);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float degree = (float)animation.getAnimatedValue();
                start = circleC * degree;
                if((degree == 0.6 || degree > 0.6) && !isChangeEnd){
                    isChangeEnd = true;
                    nowDegree = degree;
                }
                if(isChangeEnd){
                    end = (degree - nowDegree) / (1 - nowDegree) * circleC;
                    if(end < 0){
                        end = 0;
                    }
                }
                if(degree < 0.5 && isChangeEnd){
                    isChangeEnd = false;
                }
                invalidate();
            }
        });
        valueAnimator.start();

    }

    public static int getDefaultSize(int size, int measureSpec){
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode){
            case MeasureSpec.UNSPECIFIED:
                result = size;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }
}
