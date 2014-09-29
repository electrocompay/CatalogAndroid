package de.joli.cataloglib;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import de.joli.cataloglib.util.Utils;

public class Catalog extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        final Button buttonClose = (Button) findViewById(R.id.buttonClose);
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final ImageView imageViewTable = (ImageView) findViewById(R.id.imageViewTable);
        imageViewTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                viewCatalog("catalogustab2014.pdf");

            }
        });

        final ImageView imageViewCabs = (ImageView) findViewById(R.id.imageViewCabs);
        imageViewCabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewCatalog("cataloguscab2014.pdf");
            }
        });

    }

    private void viewCatalog(String name) {
        File outputFile = new File(Utils.getResourcesPath(this) + "pdf/", name);
        showPDF(outputFile.getPath());
    }


    private void showPDF(String s) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(s)),"application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        try {
            startActivity(intent);
        } catch (Exception e)
        {
            new AlertDialog.Builder(this).setTitle("Error").setMessage("This feature needs an external app in order to read PDF files on your phone. Would you like to download it?").setNegativeButton("No", null).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.adobe.reader&hl=en"));
                    Catalog.this.startActivity(intent);
                }
            }).show();
        }
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
