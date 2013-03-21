package tw.edu.tp.bjes.sls;

import android.os.Bundle;
import android.app.Activity;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;

public class LearningActivity extends Activity {
    private WebView web_view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);
        
        Bundle bundle = this.getIntent().getExtras();
        String scan_result = bundle.getString("SCAN_RESULT");
        web_view = (WebView) findViewById(R.id.web_view);
        WebSettings web_settings = web_view.getSettings();
        web_settings.setJavaScriptEnabled(true);
        web_settings.setJavaScriptCanOpenWindowsAutomatically(true);
        web_settings.setSupportZoom(true);
        web_settings.setAllowFileAccess(true);
        web_settings.setBuiltInZoomControls(true);
        web_settings.setSupportMultipleWindows(true);
        web_view.setWebChromeClient(new WebChromeClient());
        web_view.setWebViewClient(new WebViewClient());
        web_view.loadUrl(scan_result);
    }
}
