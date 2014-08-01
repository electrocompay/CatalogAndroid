package com.indianwebs.catalog.app.drawers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.indianwebs.catalog.app.model.IWFurniture;
import com.indianwebs.catalog.app.util.Utils;

/**
 * Created by abelmiranda on 6/30/14.
 */
public class IWDrawer {

    private final Context context;
    private ViewGroup view;
    private boolean frontView = true;
    private int offsetY = 0;

    public IWDrawer(Context context)
    {
        this.context = context;
    }

    public void drawFurniture(IWFurniture furniture)
    {

    }

    public ImageView addLayer(String imageName)
    {
        Bitmap image = Utils.getBitmapFromAsset(getContext(), imageName);
        if (image == null) {
            Log.d("MISSING IMAGE: %@", imageName);
        } else {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageBitmap(image);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(params);
            getView().addView(imageView);
            return imageView;
        }
        return  null;

    }

    public void clear()
    {
        getView().removeAllViews();
    };

    public Context getContext() {
        return context;
    }


    public ViewGroup getView() {
        return view;
    }

    public void setView(ViewGroup view) {
        this.view = view;
    }

    public boolean isFrontView() {
        return frontView;
    }

    public void setFrontView(boolean frontView) {
        this.frontView = frontView;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }
}
