package com.indianwebs.catalog.app.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.indianwebs.catalog.app.R;

/**
 * Created by abelmiranda on 6/4/14.
 */
public class Triangle extends View {


    public Triangle(Context context) {
        super(context);
    }

    public Triangle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Triangle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = 20;
        int offset = 15;

        Paint paint = new Paint();

        paint.setColor(getResources().getColor(R.color.grey_light));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);

        Path path = new Path();
        path.moveTo(getWidth() - width - offset, 0);
        path.lineTo(getWidth()- offset, getHeight());
        path.lineTo(getWidth() - 2*width - offset, getHeight());
        path.close();
        canvas.drawPath(path, paint);
    }
}
