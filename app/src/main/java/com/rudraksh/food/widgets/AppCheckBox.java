package com.rudraksh.food.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.CheckBox;

import com.rudraksh.food.R;


/**
 * Created by dell3 on 5/4/16.
 */
public class AppCheckBox extends CheckBox {

    public AppCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (isInEditMode())
            return;

        final TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.fontStyle);
        final String ttfName = ta.getText(0).toString();

        final Typeface font = Typeface.createFromAsset(context.getAssets(), ttfName + ".ttf");
        setTypeface(font);
        ta.recycle();

    }
}
