package de.joli.cataloglib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public abstract class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(getImageResourceId());

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, getActivityType());
                startActivity(intent);
                finish();
            }
        }, 3000);

   }

    protected abstract int getImageResourceId();

    protected abstract Class<?> getActivityType();

}
