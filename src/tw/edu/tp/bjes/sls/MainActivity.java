package tw.edu.tp.bjes.sls;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    private String scan_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // find views
        Button btn_student_login = (Button) findViewById(R.id.btn_student_login);
        btn_student_login.setOnClickListener(startScanner);
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
                scan_result = data.getStringExtra("SCAN_RESULT");
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, LearningActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("SCAN_RESULT", scan_result);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
