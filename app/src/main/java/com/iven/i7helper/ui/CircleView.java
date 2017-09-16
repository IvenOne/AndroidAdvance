package com.iven.i7helper.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.iven.i7helper.R;
import com.iven.i7helper.base.Config;

/**
 * Created by Iven on 2017/9/11.
 */

public class CircleView extends ImageView {

    private Path mPath = new Path();
    private Paint mPaint = new Paint();


    private int mBorderWidth = Config.DEFAULT_BORDER_WIDTH;
    private int mBorderColor = Config.DEFAULT_BORDER_COLOR;
    private int mFillColor = Config.DEFAULT_FILL_COLOR;
    private boolean mISFill = Config.DEFAULT_BOOL_FILL;


    private boolean mSetupPending;
    private boolean mReady;
    private boolean mDisableCircularTransformation;

    private Bitmap mBitmap;
    private BitmapShader mBitmapShader;
    private int mBitmapHeight;
    private int mBitmapWidth;

    private Paint mBitmapPaint = new Paint();
    private Paint mBorderPaint = new Paint();
    private Paint mFillPaint = new Paint();
    private Matrix mShaderMatrix = new Matrix();


    private RectF mBorderRectf = new RectF();
    private RectF mDrawableRectf = new RectF();

    private float mBborderRedius;
    private float mDrawableRedius;

    private ColorFilter mColorFilter;



    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleView,defStyleAttr,0);

        mBorderColor = a.getColor(R.styleable.CircleView_civ_border_color,Config.DEFAULT_BORDER_COLOR);
        mBorderWidth = a.getDimensionPixelSize(R.styleable.CircleView_civ_border_width,Config.DEFAULT_BORDER_WIDTH);
        mFillColor = a.getColor(R.styleable.CircleView_civ_fill_color,Config.DEFAULT_FILL_COLOR);
        mISFill = a.getBoolean(R.styleable.CircleView_civ_border_overlay,Config.DEFAULT_BOOL_FILL);

        a.recycle();
        init();

    }


    private void init(){
        super.setScaleType(Config.DEFAULT_SCALETYPE);

        mReady = true;
        if(mSetupPending){
            setup();
            mSetupPending = false;
        }
    }

    private void setup() {
        if(!mReady){
            mSetupPending = true;
            return;
        }

        if(getWidth() == 0 && getHeight() == 0) return;

        if(mBitmap == null){
            invalidate();
            return;
        }

        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setShader(mBitmapShader);

        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setColor(mBorderColor);
        mBorderPaint.setStrokeWidth(mBorderWidth);

        mFillPaint.setAntiAlias(true);
        mFillPaint.setStyle(Paint.Style.FILL);
        mFillPaint.setColor(mFillColor);

        mBitmapHeight = mBitmap.getHeight();
        mBitmapWidth = mBitmap.getWidth();

        mBorderRectf.set(calculateBounds());
        mBborderRedius = Math.min((mBorderRectf.width()-mBorderWidth)/2f,(mBorderRectf.height()-mBorderColor)/2f);


        mDrawableRectf.set(mBorderRectf);
        if(!mISFill && mBorderWidth > 0){
            mDrawableRectf.inset(mBorderWidth - 1f,mBorderWidth - 1f);
        }

        mDrawableRedius = Math.min(mDrawableRectf.width()/2f,mDrawableRectf.height()/2);

        applyColorFilter();
        updateShaderMatrix();
        invalidate();

    }

    private void applyColorFilter() {
        if(mBitmapPaint != null){
            mBitmapPaint.setColorFilter(mColorFilter);
        }
    }

    private RectF calculateBounds() {
        int availableWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        int availableHeight = getHeight() - getPaddingTop() - getPaddingBottom();

        int sideLength = Math.min(availableHeight,availableWidth);

        float left = getPaddingLeft() + (availableWidth - sideLength)/2f;
        float top = getPaddingTop() + (availableHeight - sideLength)/2f;

        return new RectF(left,top,left+sideLength,top+sideLength);
    }

    public int getmBorderWidth() {
        return mBorderWidth;
    }

    public void setmBorderWidth(int mBorderWidth) {
        if(mBorderWidth == this.mBorderWidth){
            return;
        }

        this.mBorderWidth = mBorderWidth;
        setup();
    }

    public int getmBorderColor() {
        return mBorderColor;
    }

    public void setmBorderColor(int mBorderColor) {
        if(this.mBorderColor == getContext().getResources().getColor(mBorderColor)) return;

        this.mBorderColor = getContext().getResources().getColor(mBorderColor);
        mBorderPaint.setColor(this.mBorderColor);
        invalidate();
    }

    public int getmFillColor() {
        return mFillColor;
    }

    public void setmFillColor( int mFillColor) {
        if(this.mFillColor == getContext().getResources().getColor(mFillColor)) return;
        this.mFillColor = getContext().getResources().getColor(mFillColor);
        mFillPaint.setColor(this.mFillColor);
        invalidate();
    }

    public boolean isDisableCircularTransformation() {
        return mDisableCircularTransformation;
    }

    public void setDisableCircularTransformation(boolean disableCircularTransformation) {
        if (mDisableCircularTransformation == disableCircularTransformation) {
            return;
        }

        mDisableCircularTransformation = disableCircularTransformation;
        initializeBitmap();
    }

    private void initializeBitmap() {
        if (mDisableCircularTransformation) {
            mBitmap = null;
        } else {
            mBitmap = getBitmapFromDrawable(getDrawable());
        }
        setup();
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {

        if (drawable == null) return null;
        if (drawable instanceof BitmapDrawable) return ((BitmapDrawable) drawable).getBitmap();

        try {
            Bitmap bitmap;
            if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(Config.COLORDRAWABLE_DIMENSION, Config.COLORDRAWABLE_DIMENSION, Config.BITMAP_CONFIG);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.BITMAP_CONFIG);
            }
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void updateShaderMatrix() {
        float scale;
        float dx = 0;
        float dy = 0;

        mShaderMatrix.set(null);

        if (mBitmapWidth * mDrawableRectf.height() > mDrawableRectf.width() * mBitmapHeight) {
            scale = mDrawableRectf.height() / (float) mBitmapHeight;
            dx = (mDrawableRectf.width() - mBitmapWidth * scale) * 0.5f;
        } else {
            scale = mDrawableRectf.width() / (float) mBitmapWidth;
            dy = (mDrawableRectf.height() - mBitmapHeight * scale) * 0.5f;
        }

        mShaderMatrix.setScale(scale, scale);
        mShaderMatrix.postTranslate((int) (dx + 0.5f) + mDrawableRectf.left, (int) (dy + 0.5f) + mDrawableRectf.top);

        mBitmapShader.setLocalMatrix(mShaderMatrix);
    }

    @Override
    public ColorFilter getColorFilter() {
//        return super.getColorFilter();
        return mColorFilter;
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
//        super.setColorFilter(cf);
        if(cf == mColorFilter)return;
        mColorFilter = cf;
        applyColorFilter();
        invalidate();
    }

    @Override
    public ScaleType getScaleType() {
        return Config.DEFAULT_SCALETYPE;
    }

    @Override
    public void setAdjustViewBounds(boolean adjustViewBounds) {
        if (adjustViewBounds) {
            throw new IllegalArgumentException("adjustViewBounds not supported.");
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setup();
    }

    @Override
    public void setScaleType(ScaleType scaleType) {
        if(scaleType != Config.DEFAULT_SCALETYPE){
            throw new IllegalArgumentException(String.format("ScaleType %s does not support",scaleType));
        }
    }

    @Override
    public void setImageResource(@DrawableRes int resId) {
        super.setImageResource(resId);
        initializeBitmap();
    }

    @Override
    public void setImageURI(@Nullable Uri uri) {
        super.setImageURI(uri);
        initializeBitmap();
    }

    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
        initializeBitmap();
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        initializeBitmap();
    }

    @Override
    protected void onDraw(Canvas canvas) {


        if(mDisableCircularTransformation){
            super.onDraw(canvas);
            return;
        }
        if(mBitmap == null) return;
        if(mFillColor != Color.TRANSPARENT){
            canvas.drawCircle(mDrawableRectf.centerX(),mDrawableRectf.centerY(),mDrawableRedius,mFillPaint);
        }
        canvas.drawCircle(mDrawableRectf.centerX(),mDrawableRectf.centerY(),mDrawableRedius,mBitmapPaint);

        if(mBorderWidth > 0){
            canvas.drawCircle(mDrawableRectf.centerX(),mDrawableRectf.centerY(),mBborderRedius,mBorderPaint);
        }

    }
}
