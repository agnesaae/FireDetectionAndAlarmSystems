package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.content.Context;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LiveStreaming extends Activity {

    private WebView liveWeb;
    //GridView gridView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_streaming);
        /*
        gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setAdapter(new ImageAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),PhotoScreen.class);
                intent.putExtra("id",position);
                startActivity(intent);
            }
        });
        */
        liveWeb = (WebView) findViewById(R.id.liveWeb);
        liveWeb.setWebViewClient(new WebViewClient());
        liveWeb.loadUrl("https://drive.google.com/drive/folders/186Ib-mfgxIWEJaIfzgWyTrpF7eF_YtBQ?usp=sharing");

        WebSettings webSettings = liveWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);


    }
        /*
    @Override
    public void onBackPressed() {
        if(liveWeb.canGoBack()){
            liveWeb.goBack();
        }
        else{
            super.onBackPressed();
        }
    }*/
}
