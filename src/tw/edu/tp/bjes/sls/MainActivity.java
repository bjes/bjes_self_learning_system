package tw.edu.tp.bjes.sls;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class MainActivity extends Activity {
    private String scan_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // start learning button
        Button btn_student_login = (Button) findViewById(R.id.btn_student_login);
        btn_student_login.setOnClickListener(startScanner);

        // about dev team button
        Button btn_dev_team = (Button) findViewById(R.id.btn_dev_team);
        btn_dev_team.setOnClickListener(showDevTeam);
    }

    private OnClickListener startScanner = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        }
    };
    
    private OnClickListener showDevTeam = new OnClickListener() {
        @Override
        public void onClick(View v) {
            new AlertDialog.Builder(MainActivity.this)
            .setTitle(R.string.dev_team)
            .setMessage(R.string.dev_team_msg)
            .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialoginterface, int i) {
                    // nothing need to do
                }
            })
            .show();
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

    /* 目前我們不需要設定選單
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    */

}
