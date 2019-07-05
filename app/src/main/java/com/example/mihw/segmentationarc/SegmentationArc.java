package com.example.mihw.segmentationarc;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class SegmentationArc extends View {

    private static final String TAG = "SegmentationArc";
    private int arcWidth;//环形的宽度
    private Paint paint;//定义画笔
    private int arcRadius;//圆环的半径
    private RectF rectf;
    private float startAngle = 135;//开始角度
    private Context mContext;
    private float curWeight;
    private float[] standWeights;
    private int[] standColors;
    //每一个弧的开始间隔和结束间隔
    private int startDuration = 5;
    private int endDuration = 10;

    public void setRoundwidth(int roundwidth) {
        this.arcWidth = roundwidth;
    }
    public void curWeight(float weight) {
        this.curWeight = weight;
    }

    public void setStandWeights(float[] weights) {
        this.standWeights = weights;
    }

    public void setStandColors(int[] colors) {
        this.standColors = colors;
    }
    private int MARGINTOP = Utils.dip2px(20) ;//设置距离上边距的距离
    private float firststart;//第一个开始的角度

    private float firstend;//第一个结束的角度


    public SegmentationArc(Context context) {
        super(context);
        this.mContext = context;
    }

    public SegmentationArc(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        setArcAttributes(attrs);

    }


    public SegmentationArc(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        setArcAttributes(attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        //获取圆的半径
        arcRadius = getWidth() / 2 - Utils.dip2px(15);
        rectf = new RectF(getWidth() / 2 - arcRadius+Utils.dip2px(10), MARGINTOP, getWidth() / 2 + arcRadius-Utils.dip2px(10), 2 * arcRadius);
        //绘制内虚线圆
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(getResources().getColor(R.color.black1));
        PathEffect effects = new DashPathEffect(new float[]{Utils.dip2px(1),Utils.dip2px(10)},1);
        paint.setPathEffect(effects);
        paint.setStrokeWidth(Utils.dip2px(1.5f));
        canvas.drawCircle(rectf.centerX(), rectf.centerX()-MARGINTOP/4, arcRadius-arcWidth*2, paint);


        //重新设置画圆的风格和圆的宽度
        paint.setPathEffect(null);
        paint.setStrokeWidth(arcWidth);

        //绘制背景圆
        paint.setColor(getResources().getColor(R.color.dy_weight_bg));//灰颜色
        firststart = startAngle;
        firstend = 90;
        canvas.drawArc(rectf, firststart + startDuration, firstend - endDuration, false, paint);

        firststart = firststart + firstend;
        firstend = 90;
        canvas.drawArc(rectf, firststart + startDuration, firstend - endDuration, false, paint);

        firststart = firststart + firstend;
        firstend = 90;
        canvas.drawArc(rectf, firststart + startDuration, firstend - endDuration, false, paint);

        firststart = firststart + firstend;
        firstend = 90;
        canvas.drawArc(rectf, firststart + startDuration, firstend - endDuration, false, paint);

        if(curWeight==0)return;

        if (curWeight < standWeights[0]) {
            paint.setColor(getResources().getColor(standColors[0]));//蓝颜色
            firststart = startAngle;
            firstend = 1;
            canvas.drawArc(rectf, firststart + startDuration, firstend, false, paint);
        } else if (curWeight >= standWeights[0] && curWeight < standWeights[1]) {//1区间
            paint.setColor(getResources().getColor(standColors[0]));//蓝颜色
            firststart = startAngle;
            firstend = (curWeight - standWeights[0]) / (standWeights[1] - standWeights[0]) * 90;
            canvas.drawArc(rectf, firststart + startDuration, firstend - endDuration, false, paint);

        } else if (curWeight >= standWeights[1] && curWeight < standWeights[2]) {//2区间
            paint.setColor(getResources().getColor(standColors[0]));//蓝颜色
            firststart = startAngle;
            firstend = 90;
            canvas.drawArc(rectf, firststart + startDuration, firstend - endDuration, false, paint);

            paint.setColor(getResources().getColor(standColors[1]));//绿颜色
            firststart = firststart + firstend;
            firstend = (curWeight - standWeights[1]) / (standWeights[2] - standWeights[1]) * 90;
            if (firstend <= endDuration) {
                firstend = 10.1f;
            }
            canvas.drawArc(rectf, firststart + startDuration, firstend - endDuration, false, paint);
        } else if (curWeight >= standWeights[2] && curWeight < standWeights[3]) {//3区间
            paint.setColor(getResources().getColor(standColors[0]));//蓝颜色
            firststart = startAngle;
            firstend = 90;
            canvas.drawArc(rectf, firststart + startDuration, firstend - endDuration, false, paint);

            paint.setColor(getResources().getColor(standColors[1]));//绿颜色
            firststart = firststart + firstend;
            firstend = 90;
            canvas.drawArc(rectf, firststart + startDuration, firstend - endDuration, false, paint);

            paint.setColor(getResources().getColor(standColors[2]));//黄颜色
            firststart = firststart + firstend;
            firstend = (curWeight - standWeights[2]) / (standWeights[3] - standWeights[2]) * 90;
            if (firstend <= endDuration) {
                firstend = 10.1f;
            }
            canvas.drawArc(rectf, firststart + startDuration, firstend - endDuration, false, paint);
        } else if (curWeight >= standWeights[3]) {//4区间
            paint.setColor(getResources().getColor(standColors[0]));//蓝颜色
            firststart = startAngle;
            firstend = 90;
            canvas.drawArc(rectf, firststart + startDuration, firstend - endDuration, false, paint);

            paint.setColor(getResources().getColor(standColors[1]));//绿颜色
            firststart = firststart + firstend;
            firstend = 90;
            canvas.drawArc(rectf, firststart + startDuration, firstend - endDuration, false, paint);

            paint.setColor(getResources().getColor(standColors[2]));//黄颜色
            firststart = firststart + firstend;
            firstend = 90;
            canvas.drawArc(rectf, firststart + startDuration, firstend - endDuration, false, paint);

            paint.setColor(getResources().getColor(standColors[3]));//红颜色
            firststart = firststart + firstend;
            if (curWeight > standWeights[4]) {
                firstend = 90;
            } else {
                firstend = (curWeight - standWeights[3]) / (standWeights[4] - standWeights[3]) * 90;
                if (firstend <= endDuration) {
                    firstend = 10.1f;
                }
            }
            canvas.drawArc(rectf, firststart + startDuration, firstend - endDuration, false, paint);
        }
        paint.setStrokeWidth(Utils.dip2px(22));
        paint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawArc(rectf, firststart+ startDuration+firstend-9, 3, false, paint);
    }

    private void setArcAttributes(AttributeSet attrs) {
        //此处可使用styleable中自定义属性
//        TypedArray a = mContext.obtainStyledAttributes(attrs,
//                R.styleable.arc);
//        arcWidth = a.getInteger(R.styleable.arcwidth, 25);
//        a.recycle();
    }

}
