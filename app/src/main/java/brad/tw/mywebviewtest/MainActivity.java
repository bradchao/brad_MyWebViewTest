package brad.tw.mywebviewtest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

        if (Build.VERSION.SDK_INT>=23) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);
            }
        }

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
        webview.loadUrl("javascript:moveTo(24.150544,120.683169)");
    }


}
