package brad.tw.mywebviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webview = (WebView)findViewById(R.id.webview);
        initWebView();

    }

    private void initWebView(){
        WebViewClient client = new WebViewClient();
        webview.setWebViewClient(client);


        //webview.loadUrl("http://www.yahoo.com");
        webview.loadUrl("file:///android_asset/brad.html");
    }

    public void test1(View v){
        //webview.goBack();
        //webview.reload();
    }
    public void test2(View v){
        //webview.goForward();
    }


}
