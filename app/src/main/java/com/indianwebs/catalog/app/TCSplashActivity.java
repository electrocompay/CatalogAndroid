package com.indianwebs.catalog.app;

import de.joli.cataloglib.SplashActivity;

/**
 * Created by abel.miranda on 9/24/14.
 */
public class TCSplashActivity extends SplashActivity {

    @Override
    protected int getImageResourceId() {
        return R.drawable.splash_tables_1024_768;
    }

    @Override
    protected Class<?> getActivityType() {
        return TCModelActivity.class;
    }

    @Override
    protected long getExpansionFileSize() {
        return 330451026;
    }

    @Override
    protected int getExpansionVersion() {
        return 2;
    }
}
