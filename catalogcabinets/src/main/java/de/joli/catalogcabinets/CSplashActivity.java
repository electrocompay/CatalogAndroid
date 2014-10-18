package de.joli.catalogcabinets;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import de.joli.cataloglib.SplashActivity;

/**
 * Created by abel.miranda on 9/24/14.
 */
public class CSplashActivity extends SplashActivity {

    @Override
    protected int getImageResourceId() {
        return R.drawable.splash_cabinets_1024_768;
    }

    @Override
    protected Class<?> getActivityType() {
        return CModelActivity.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setTextColor(Color.BLACK);
    }

    @Override
    protected long getExpansionFileSize() {
        return 1009456691;
    }

    @Override
    protected int getExpansionVersion() {
        return 9;
    }
}
