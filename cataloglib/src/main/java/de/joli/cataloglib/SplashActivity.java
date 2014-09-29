package de.joli.cataloglib;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.vending.expansion.downloader.DownloaderClientMarshaller;
import com.google.android.vending.expansion.downloader.Helpers;

import java.io.File;
import java.io.IOException;

import de.joli.cataloglib.expansion.ExpansionDownloader;
import de.joli.cataloglib.util.OnProgressListener;
import de.joli.cataloglib.util.Utils;

public abstract class SplashActivity extends Activity{

    private class UnzipAsyncTask extends AsyncTask<String, Integer, Void>
    {

        private ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        private TextView textView = (TextView) findViewById(R.id.textView);

        @Override
        protected Void doInBackground(String... strings) {
            try {
                Utils.unzip(SplashActivity.this, Utils.getExpansionFile(SplashActivity.this), strings[0], new OnProgressListener() {
                    @Override
                    public void progress(int value) {
                        publishProgress(value);
                    }

                    @Override
                    public void getTotalEntries(final int totalEntries) {
                        SplashActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.VISIBLE);
                                progressBar.setMax(totalEntries);
                                textView.setVisibility(View.VISIBLE);
                                textView.setText("Deploying resources");
                            }
                        });
                    }

                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            gotoMainActivity();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String APP_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmAV0pAJ+fFqkXrferkbeBUx/jNRsKQYD1J3haVzpLUaUC/QREWLKqRJm85/TdjyN0fDM57eLjAUTtIY5bnEawbhPsiqjjWezbFaX1i0M4FKgvzGoNIaLrL99OiRTNOj6uCmpEwVVCQnN7yiqt/03l9VptUtpB4CslLQLpZhWh59ufhNS9V5U9IIJAQDNFwKQhMJbKF3dtrvr+14xRsVw92gs7twF9qQmYbs81YUsWDuG84oV86LMNzc9ht3c8MgO+xXgUnZVroLKln1RrN9oqQX5g4rL7t+kLTvH8yQK+zKxcX87GstOy25OIF7s7gSxke/1DkTGppRszaShE8kp5QIDAQAB";


        if (!expansionFilesDelivered()) {
            // Build an Intent to start this activity from the Notification
            Intent notifierIntent = new Intent(this, SplashActivity.class);
            notifierIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                    notifierIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            // Start the download service (if required)
            int startResult = 0;
            try {
                startResult = DownloaderClientMarshaller.startDownloadServiceIfRequired(this, pendingIntent, ExpansionDownloader.class);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            // If download has started, initialize this activity to show
            // download progress
            if (startResult != DownloaderClientMarshaller.NO_DOWNLOAD_REQUIRED) {
                // This is where you do set up to display the download
                // progress (next step)

                return;
            } // If the download wasn't necessary, fall through to start the app
        }
        setContentView(R.layout.activity_splash);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(getImageResourceId());

        boolean closeSplash = true;
        if (isObbDownloaded()){
            if (!isObbUnzipped()){
                unzipObb();
                closeSplash = false;
            }
        } else {
            downloadObb();
            unzipObb();
            closeSplash = false;
        }

        if (closeSplash) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    gotoMainActivity();
                }
            }, 3000);
        }

   }

    private void gotoMainActivity() {
        Intent intent = new Intent(this, getActivityType());
        startActivity(intent);
        finish();
    }

    private void downloadObb() {

    }

    private void unzipObb() {
        String resourcePath = Utils.getResourcesPath(this);
        File file = new File(resourcePath);
        if (!file.exists()){
            new UnzipAsyncTask().execute(resourcePath);
        }
    }

    private boolean isObbUnzipped() {
        File obbFile = new File(Utils.getResourcesPath(this));
        return obbFile.exists();
    }

    private boolean isObbDownloaded() {
        File obbFile = new File(Utils.getExpansionFile(this));
        return obbFile.exists();
    }

    protected abstract int getImageResourceId();

    protected abstract Class<?> getActivityType();

    boolean expansionFilesDelivered() {
            String fileName = Helpers.getExpansionAPKFileName(this, true,
                    1);
            if (!Helpers.doesFileExist(this, fileName, 279708549, false))
                return false;
        return true;
    }

}
