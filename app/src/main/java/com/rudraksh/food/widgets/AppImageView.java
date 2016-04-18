
package com.rudraksh.food.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;

import com.rudraksh.food.R;
import com.rudraksh.food.utils.PicassoBigCache;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class AppImageView extends FrameLayout implements Target {

    private ImageView imageView;
    private ImageView placeHolderImageView;
    private ProgressBar progressBar;
    private Context context;
    private boolean isGradient = false;
    private LayoutParams imageParams;

    public AppImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public AppImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AppImageView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context c) {
        this.context = c;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        imageView = new ImageView(context);
        placeHolderImageView = new ImageView(context);
        placeHolderImageView.setImageResource(R.drawable.ic_location);
        imageView.setScaleType(ScaleType.FIT_XY);

        progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleSmall);
        progressBar.setIndeterminate(true);
        final LayoutParams progressParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        progressParams.gravity = Gravity.CENTER;

        imageParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        imageParams.gravity = Gravity.CENTER;

        addView(placeHolderImageView, progressParams);
        addView(imageView, imageParams);
        addView(progressBar, progressParams);

    }

    public void loadImage(String url) {
        if (!TextUtils.isEmpty(url))
            PicassoBigCache.INSTANCE.getPicassoBigCache(context).load(url).into(this);
        else {
            removeView(progressBar);
            setImageScaleType(ScaleType.CENTER);
            imageView.setImageResource(R.drawable.ic_location);
        }
    }

    public void loadImage(String url, int emptyID) {
        PicassoBigCache.INSTANCE.getPicassoBigCache(context).load(url).placeholder(emptyID).into(this);
    }

    public void loadImage(String url, int emptyID, int failID) {
        PicassoBigCache.INSTANCE.getPicassoBigCache(context).load(url).error(failID).placeholder(emptyID).into(this);
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        removeView(progressBar);
        removeView(placeHolderImageView);
        imageView.setScaleType(ScaleType.CENTER_CROP);
        imageView.setImageBitmap(bitmap);
        if (isGradient && getChildCount() == 1) {
            final View gradientView = new View(context);
            gradientView.setBackgroundResource(R.drawable.gradient_drawable);
            addView(gradientView, imageParams);
        }
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {
        removeView(progressBar);
    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void setImageScaleType(ScaleType scaleType) {
        imageView.setScaleType(scaleType);
    }

    public void setGradient(boolean gradient) {
        isGradient = gradient;
    }
}
