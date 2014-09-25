package de.joli.cataloglib.views;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import de.joli.cataloglib.R;
import de.joli.cataloglib.util.Utils;


/**
 * Created by abelmiranda on 7/6/14.
 */
public class IWOptionView extends FrameLayout {

    private TextView label;
    private boolean selected;
    private String image;
    private ImageView imageView;

    public TextView getLabel() {
        return label;
    }

    public void setLabel(TextView label) {
        this.label = label;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        updateViewState();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
        Bitmap img = Utils.getBitmapFromAsset(getContext(), image);
        imageView.setImageBitmap(img);
    }

    public IWOptionView(Context context) {
        super(context);
        init();
    }

    public IWOptionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IWOptionView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.option_view, this);
        label = (TextView) findViewById(R.id.textview);
        imageView = (ImageView) findViewById(R.id.imageView);
        selected = false;
        updateViewState();
    }

    private void updateViewState() {
        if (!isInEditMode()) {
            if (selected) {
                label.setBackgroundColor(getContext().getResources().getColor(R.color.grey_dark));
                label.setTextColor(Color.WHITE);
            } else {
                label.setBackgroundColor(getContext().getResources().getColor(R.color.grey_light));
                label.setTextColor(Color.BLACK);
            }
        }
    }

    void clearSelection()
    {
        setSelected(false);
    };

}
