package tw.edu.tp.bjes.sls;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.widget.Button;

public class LearningActivity extends Activity {
    private WebView web_view;
    private String current_url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

        // scan button
        Button btn_scan = (Button) findViewById(R.id.btn_scan);
        btn_scan.setOnClickListener(startScanner);

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

    private OnClickListener startScanner = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                current_url = data.getStringExtra("SCAN_RESULT");
                web_view.loadUrl(current_url);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        web_view.loadUrl("");
    }

    @Override
    public void onResume() {
        super.onResume();
        web_view.loadUrl(current_url);
    }
}
