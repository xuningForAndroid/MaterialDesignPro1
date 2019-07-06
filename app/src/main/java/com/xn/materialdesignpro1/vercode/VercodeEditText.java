package com.xn.materialdesignpro1.vercode;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.xn.materialdesignpro1.R;

/**
 * 验证码输入框
 */
public class VercodeEditText extends AppCompatEditText implements
        VertificationAction ,TextWatcher{

    //验证码位数
    private int mFigures;
    //选中底线颜色
    private int mSelectedLineColor;
    //间距
    private int mVerCodeMargin;
    //正常颜色
    private int mNormalColor;
    //选中背景颜色
    private int mSelectedBackColor;
    //底线高度
    private float mBottomLineHeight;
    private int mEachCodeLen;
    private Paint mSelectedBackPaint;
    private Paint mNormalBackgroundPaint;
    private Paint mSelectedBottomLinePaint;
    private Paint mNormalBottomLinePaint;
    private int mCurrentPosition;
    private onVerficationCodeChangeListener onVercodeChangeListener;

    public VercodeEditText(Context context) {
        this(context,null);
    }

    public VercodeEditText(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public VercodeEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context,attrs);
        setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));//防止出现下划线
        initPaint();
        setFocusableInTouchMode(true);
        super.addTextChangedListener(this);
    }

    /**
     * 属性初始化
     * @param attrs
     */
    private void initAttrs(Context context,AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.VercodeEditText);
        mFigures = typedArray.getInteger(R.styleable.VercodeEditText_figures, 6);
        mSelectedLineColor = typedArray.getColor(R.styleable.VercodeEditText_selectedColor, Color.RED);
        mVerCodeMargin = (int) typedArray.getDimension(R.styleable.VercodeEditText_vercodeMargin, 10);
        mNormalColor = typedArray.getColor(R.styleable.VercodeEditText_normalColor, Color.GREEN);
        mSelectedBackColor = typedArray.getColor(R.styleable.VercodeEditText_selectedBackgroundColor, android.R.color.transparent);
        mBottomLineHeight = typedArray.getDimension(R.styleable.VercodeEditText_bottomLineHeight, 3);


        typedArray.recycle();
    }

//    @Override
//    public void setCursorVisible(boolean visible) {
//        super.setCursorVisible(true);//隐藏显示的光标
//    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (event.getAction()==MotionEvent.ACTION_DOWN){
//
//            requestFocus();
//            setSelection(getText().length());
//            showKeyBorad(getContext());
//            return false;
//        }
//        return super.onTouchEvent(event);
//    }



    @Override
    public void setSelection(int index) {
        super.setSelection(index);

    }

    private void showKeyBorad(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(this, InputMethodManager.SHOW_FORCED);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mWidth=0;
        int mHeight=0;

        int wideMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (wideMode==MeasureSpec.EXACTLY)
            mWidth=widthSize;
        else
            mWidth=getScreenWidth(getContext());

        //获取每位验证码的宽度
        mEachCodeLen =  (mWidth - (mVerCodeMargin * (mFigures - 1))) / mFigures;

        if (heightMode==MeasureSpec.EXACTLY)
            mHeight=heightSize;
        else
            mHeight= mEachCodeLen;


        setMeasuredDimension(mWidth,mHeight);
    }

    public void initPaint(){
        mSelectedBackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSelectedBackPaint.setColor(mSelectedBackColor);

        mNormalBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mNormalBackgroundPaint.setColor(getColor(android.R.color.transparent));

        mSelectedBottomLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSelectedBottomLinePaint.setColor(mSelectedLineColor);
        mSelectedBottomLinePaint.setStrokeWidth(mBottomLineHeight);

        mNormalBottomLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mNormalBottomLinePaint.setColor(mNormalColor);
        mNormalBottomLinePaint.setStrokeWidth(mBottomLineHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        //获取验证码位数
        mCurrentPosition = getText().length();
        int width = mEachCodeLen - getPaddingLeft() - getPaddingRight();
        int height = mEachCodeLen - getPaddingBottom() - getPaddingTop();
        //循环绘制底线 背景
        for (int i = 0; i < mFigures; i++) {
            canvas.save();
            int start = i * (width + mVerCodeMargin);
            int end=width+start;

            if (i==mCurrentPosition)
                canvas.drawRect(start,0,end,height,mSelectedBackPaint);
            else
                canvas.drawRect(start,0,end,height,mNormalBackgroundPaint);

            canvas.restore();
        }
        //绘制文字
        String value = getText().toString();
        for (int i = 0; i < value.length(); i++) {
            canvas.save();
            int start = width * i + i * mVerCodeMargin;
            float x = start + width / 2;
            TextPaint paint = getPaint();
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setColor(getCurrentTextColor());
            Paint.FontMetrics fontMetrics = paint.getFontMetrics();
            float baseline = (height - fontMetrics.bottom + fontMetrics.top) / 2
                    - fontMetrics.top;
            canvas.drawText(String.valueOf(value.charAt(i)), x, baseline, paint);

            canvas.restore();
        }

        //绘制底线
        for (int i = 0; i < mFigures; i++) {
            canvas.save();
            float lineY = height - mBottomLineHeight / 2;
            int start = width * i + i * mVerCodeMargin;
            int end = width + start;
            if (i < mCurrentPosition) {
                canvas.drawLine(start, lineY, end, lineY, mSelectedBottomLinePaint);
            } else {
                canvas.drawLine(start, lineY, end, lineY, mNormalBottomLinePaint);
            }
            canvas.restore();
        }
    }

    private int getColor(int color) {
        return ContextCompat.getColor(getContext(), color);
    }
    /**
     * dp转px
     */
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    //获取屏幕的宽度
    private int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    @Override
    public void setFigures(int figures) {
        this.mFigures=figures;
        postInvalidate();
    }

    @Override
    public void setCodeMargins(int margin) {
        this.mVerCodeMargin=margin;
        postInvalidate();
    }

    @Override
    public void setBottomSelectedColor(int selectedColor) {
        this.mSelectedLineColor=selectedColor;
        postInvalidate();
    }

    @Override
    public void setNormalLineColor(int normalLineColor) {
        this.mNormalColor=normalLineColor;
        postInvalidate();
    }

    @Override
    public void setSelectedBackgroundColor(int selectedBackgroundColor) {
        this.mSelectedBackColor=selectedBackgroundColor;
        postInvalidate();
    }

    @Override
    public void setBottomLineHeight(int bottomLineHeight) {
        this.mBottomLineHeight=bottomLineHeight;
        postInvalidate();
    }


    public void setOnVercodeChangedListener(onVerficationCodeChangeListener onVercodeChangedListener) {
        this.onVercodeChangeListener=onVercodeChangedListener;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        mCurrentPosition= getText().length();
        postInvalidate();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mCurrentPosition= getText().length();
        postInvalidate();
        if (onVercodeChangeListener!=null){
            onVercodeChangeListener.onVerCodeChanged(s,start,before,count);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        mCurrentPosition= getText().length();
        postInvalidate();

        if (getText().length() ==mFigures){
            if (onVercodeChangeListener!=null)
                onVercodeChangeListener.onInputCompleted(getText());
        }else if (getText().length()>mFigures){
            getText().delete(mFigures,getText().length());//防止输入溢出
        }
    }
}
