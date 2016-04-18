package com.rudraksh.food.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.rudraksh.food.R;


/**
 * Created by dell3 on 7/4/16.
 */
public class AppImageButton extends ImageButton {

    public AppImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (isInEditMode())
            return;

        final TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.fontStyle);
        final String ttfName = ta.getText(0).toString();

        ta.recycle();

    }
}
