package brad.tw.mywebviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private WebView webview;
    private EditText myname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myname = (EditText)findViewById(R.id.myname);
        webview = (WebView)findViewById(R.id.webview);
        initWebView();

    }

    private void initWebView(){
        WebViewClient client = new WebViewClient();
        webview.setWebViewClient(client);

        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);

        //webview.loadUrl("http://www.yahoo.com");
        webview.loadUrl("file:///android_asset/map.html");
    }

    public void test1(View v){
        //webview.goBack();
        //webview.reload();

        webview.loadUrl("javascript:jtest2('" +
                myname.getText().toString()
                + "')");
    }
    public void test2(View v){
        //webview.goForward();
    }


}
