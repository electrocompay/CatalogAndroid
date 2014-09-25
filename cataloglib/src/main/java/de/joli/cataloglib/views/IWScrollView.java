package de.joli.cataloglib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * Created by abel.miranda on 9/20/14.
 */
public class IWScrollView extends HorizontalScrollView{

    public interface OnScrollListener{

        public void scrolled();

    }

    private OnScrollListener onScrollListener;

    public IWScrollView(Context context) {
        super(context);
    }

    public IWScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IWScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (onScrollListener != null){
            onScrollListener.scrolled();
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public OnScrollListener getOnScrollListener() {
        return onScrollListener;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }
}
