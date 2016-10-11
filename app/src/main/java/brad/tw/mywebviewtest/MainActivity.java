package brad.tw.mywebviewtest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private WebView webview;
    private EditText myname;
    private LocationManager lmgr;
    private MyListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);
            }
        }

        lmgr = (LocationManager) getSystemService(LOCATION_SERVICE);
        listener = new MyListener();
        lmgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);


        myname = (EditText) findViewById(R.id.myname);
        webview = (WebView) findViewById(R.id.webview);
        initWebView();

    }

    @Override
    public void finish() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lmgr.removeUpdates(listener);
        super.finish();
    }

    private class MyListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            webview.loadUrl("javascript:moveTo(" + lat + "," + lng + ")");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    public class BradJS {
        @JavascriptInterface
        public void showPos(String newpos){
            Log.v("brad", "ret => " + newpos );
        }
    }


    private void initWebView(){
        WebViewClient client = new WebViewClient();
        webview.setWebViewClient(client);

        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);

        webview.addJavascriptInterface(new BradJS(),"brad");

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
