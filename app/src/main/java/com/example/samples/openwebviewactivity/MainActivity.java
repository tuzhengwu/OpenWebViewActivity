package com.example.samples.openwebviewactivity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = (WebView) findViewById(R.id.webView1);
        webView.loadUrl("http://www.baidu.com/");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL newUrl = new URL("http://www.baidu.com/");
                    URLConnection connection = newUrl.openConnection();
                    DataInputStream dis = new DataInputStream(connection.getInputStream());
                    BufferedReader in = new BufferedReader(new InputStreamReader(dis,"UTF-8"));
                    String html = "";
                    String readLine = null;
                    while ((readLine = in.readLine()) != null){
                        html = html + readLine;
                        Log.d("OpenWebViewActivity", readLine);
                    }
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}