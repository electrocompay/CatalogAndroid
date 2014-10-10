package de.joli.cataloglib.drawers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import de.joli.cataloglib.model.IWFurniture;
import de.joli.cataloglib.util.Utils;

/**
 * Created by abelmiranda on 6/30/14.
 */
public class IWDrawer {

    private final Context context;
    private ViewGroup view;
    private boolean frontView = true;
    private int offsetY = 0;
    private Bitmap bitmap;
    private Canvas canvas;
    private ImageView imageView;
    private Paint paint = new Paint();

    public IWDrawer(Context context)
    {
        this.context = context;
        imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setPadding(20, 20, 20, 20);
        imageView.setLayoutParams(params);
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
            if (bitmap == null) {
                bitmap = Bitmap.createBitmap(image.getWidth(), image.getHeight(), Bitmap.Config.ARGB_8888);
                canvas = new Canvas(bitmap);
                imageView.setImageBitmap(image);
            }

            canvas.drawBitmap(image, 0, 0, paint);

            return imageView;
        }
        return  null;

    }

    public void clear()
    {
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            imageView.setImageBitmap(null);
            imageView.setImageBitmap(bitmap);
        }
    };

    public Context getContext() {
        return context;
    }


    public ViewGroup getView() {
        return view;
    }

    public void setView(ViewGroup view) {
        this.view = view;
        this.view.addView(imageView);
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
