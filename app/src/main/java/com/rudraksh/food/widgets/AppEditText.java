package com.rudraksh.food.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import com.rudraksh.food.R;


public class AppEditText extends EditText {

    public AppEditText(Context context, AttributeSet attrs) {
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
