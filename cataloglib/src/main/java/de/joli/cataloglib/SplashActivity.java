package de.joli.cataloglib;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Messenger;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.vending.expansion.downloader.DownloadProgressInfo;
import com.google.android.vending.expansion.downloader.DownloaderClientMarshaller;
import com.google.android.vending.expansion.downloader.DownloaderServiceMarshaller;
import com.google.android.vending.expansion.downloader.Helpers;
import com.google.android.vending.expansion.downloader.IDownloaderClient;
import com.google.android.vending.expansion.downloader.IDownloaderService;
import com.google.android.vending.expansion.downloader.IStub;

import java.io.File;
import java.io.IOException;

import de.joli.cataloglib.expansion.ExpansionDownloader;
import de.joli.cataloglib.util.OnProgressListener;
import de.joli.cataloglib.util.Utils;

public abstract class SplashActivity extends Activity implements IDownloaderClient {

    private ProgressBar progressBar;
    private TextView textView;
    private IDownloaderService mRemoteService;
    private IStub downloaderClientStub;

    private class UnzipAsyncTask extends AsyncTask<String, Integer, Void>
    {


        @Override
        protected Void doInBackground(String... strings) {
            try {
                Utils.unzip(SplashActivity.this, Utils.getExpansionFile(SplashActivity.this, getExpansionVersion()), strings[0], new OnProgressListener() {
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
        setContentView(R.layout.activity_splash);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(getImageResourceId());

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView) findViewById(R.id.textView);


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


            if (startResult != DownloaderClientMarshaller.NO_DOWNLOAD_REQUIRED) {
                // Instantiate a member instance of IStub
                downloaderClientStub = DownloaderClientMarshaller.CreateStub(this,
                        ExpansionDownloader.class);
                progressBar.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                progressBar.setMax(100);
                textView.setText("Downloading resources");

                return;
            }

        } else {

            if (!isObbUnzipped()){
                unzipObb();
                return;
            }
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    gotoMainActivity();
                }
            }, 3000);

    }

    @Override
    protected void onResume() {
        if (null != downloaderClientStub) {
            downloaderClientStub.connect(this);
        }
        super.onResume();
    }

    @Override
    protected void onStop() {
        if (null != downloaderClientStub) {
            downloaderClientStub.disconnect(this);
        }
        super.onStop();
    }

    @Override
    public void onServiceConnected(Messenger m) {
        mRemoteService = DownloaderServiceMarshaller.CreateProxy(m);
        mRemoteService.onClientUpdated(downloaderClientStub.getMessenger());
    }

    @Override
    public void onDownloadProgress(DownloadProgressInfo progress) {
        int value = (int) ((progress.mOverallProgress / (float) progress.mOverallTotal) * 100);
        progressBar.setProgress(value);
    }

    @Override
    public void onDownloadStateChanged(int newState) {
        if (newState == IDownloaderClient.STATE_COMPLETED){
            unzipObb();
        }
    }

    private void gotoMainActivity() {
        Intent intent = new Intent(this, getActivityType());
        startActivity(intent);
        finish();
    }

    private void unzipObb() {
        String resourcePath = Utils.getResourcesPath(this);
        File file = new File(resourcePath);
        if (!file.exists() || file.listFiles().length == 0){
            new UnzipAsyncTask().execute(resourcePath);
        }
    }

    protected boolean isObbUnzipped() {
        File obbFile = new File(Utils.getResourcesPath(this));
        return obbFile.exists() && obbFile.listFiles().length > 0;
    }

    protected abstract int getImageResourceId();

    protected abstract Class<?> getActivityType();

    protected boolean expansionFilesDelivered() {
            String fileName = Helpers.getExpansionAPKFileName(this, true,
                    getExpansionVersion());
            if (!Helpers.doesFileExist(this, fileName, getExpansionFileSize(), false))
                return false;
        return true;
    }

    protected abstract int getExpansionVersion();

    protected abstract long getExpansionFileSize();

}
