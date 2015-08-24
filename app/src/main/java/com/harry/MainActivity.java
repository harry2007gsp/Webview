package com.harry;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    MyWebviewClient myWebviewClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webview);
        myWebviewClient = new MyWebviewClient();
    }

    public void data(View view) {
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("http://www.yahoo.com"));
//        startActivity(intent);
//        webView.loadUrl("http://www.yahoo.com");// to access website in webview
        webView.loadUrl("file:///android_asset/www/myhtml.html");// to access my own customized html file
        webView.setWebViewClient(myWebviewClient);
        webView.getSettings().setJavaScriptEnabled(true);// to enable javascript
        MyHtml myHtml = new MyHtml(this);

        webView.addJavascriptInterface(myHtml,"abcd");
    }

    @Override
    public void onBackPressed() {
        //to add to back stack
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private class MyWebviewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
//    @android.webkit.JavascriptInterface
    public class MyHtml {
        public MyHtml(Context context) {

        }
    @android.webkit.JavascriptInterface
    public void html() {
            Log.d("test", "html code here");
        }
    }
}
