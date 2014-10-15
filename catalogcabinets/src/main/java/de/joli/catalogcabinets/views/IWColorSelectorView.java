package de.joli.catalogcabinets.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import de.joli.catalogcabinets.R;
import de.joli.cataloglib.model.IWColor;
import de.joli.cataloglib.util.Utils;
import de.joli.cataloglib.views.IWSelectorView;

/**
 * Created by abel.miranda on 10/10/14.
 */
public class IWColorSelectorView extends FrameLayout implements View.OnClickListener {


    public interface IWColorSelectorViewDelegate {

        public void didSelection(IWColorSelectorView colorSelectorView, View view);

    }

    private View customView;
    private IWColor color;
    private IWColorSelectorViewDelegate delegate;
    private Button button;
    private ImageView imageView;

    public IWColorSelectorView(Context context) {
        super(context);
        init();
    }

    public IWColorSelectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IWColorSelectorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        customView = LayoutInflater.from(getContext()).inflate(R.layout.color_selector_view, this);
        button = (Button) customView.findViewById(R.id.selector_button);
        button.setOnClickListener(this);
        imageView = (ImageView) customView.findViewById(R.id.imageView);
    }

    public IWColorSelectorViewDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(IWColorSelectorViewDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean isSelected() {
        return button.isSelected();
    }

    @Override
    public void setSelected(boolean selected) {
        button.setSelected(selected);
        if (selected) {
            button.setTextColor(Color.WHITE);
        } else {
            button.setTextColor(Color.BLACK);
        }
    }

    public IWColor getColor() {
        return color;
    }

    public void setColor(IWColor color) {
        this.color = color;
        if (color == null) {
            imageView.setImageBitmap(null);
        } else {
            Bitmap bitmap = Utils.getBitmapFromAsset(getContext(), color.getFile());
            if (bitmap != null)
                imageView.setImageBitmap(bitmap);
            if (color != null) {
                button.setText(color.getName());
            }
        }
        updateButton();
    }

    public String getText() {
        return button.getText().toString();
    }

    public void setText(String text) {
        button.setText(text);
    }

    public void updateButton() {
        if (button.isSelected()) {
            disableButtons(getRootView());
            button.setTextColor(Color.WHITE);
//            [button setBackgroundColor:[UIColor colorWithRed:0.5 green:0.5 blue:0.5 alpha:1]];
        } else {
//            [button setBackgroundColor:[UIColor colorWithRed:0.8 green:0.8 blue:0.8 alpha:1]];
            button.setTextColor(Color.BLACK);
        }

        if (isEnabled()) {
            button.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
        } else {
            button.setVisibility(View.INVISIBLE);
            imageView.setVisibility(View.INVISIBLE);
        }
    }

    public void disableButtons(View view) {
        if (view != this) {
            if (view instanceof IWColorSelectorView) {
                IWColorSelectorView selector = (IWColorSelectorView) view;
                selector.unSelect();
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    View childView = viewGroup.getChildAt(i);
                    disableButtons(childView);
                }
            }
        }
    }

    public void unSelect() {
        button.setSelected(false);
        updateButton();
    }

    ;

    @Override
    public void onClick(View view) {
        button.setSelected(true);
        updateButton();
        if (delegate != null) {
            delegate.didSelection(this, this);
        }

    }


    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        updateButton();
    }

}
