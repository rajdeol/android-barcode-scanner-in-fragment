package in.whomeninja.android_barcode_scanner;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.client.android.CaptureActivity;


public class HomeActivity extends ActionBarActivity {
    private String codeFormat,codeContent;
    private TextView formatTxt, contentTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * event handler for scan button
     * @param view view of the activity
     */
    public void scanNow(View view){
        IntentIntegrator integrator = new IntentIntegrator(this);
        // use forSupportFragment or forFragment method to use fragments instead of activity
        // use setCaptureLayout to provide the ID of layout to use for scanning
        integrator.setCaptureLayout(R.layout.scan_screen);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.setPrompt(this.getString(R.string.scan_bar_code));
        integrator.setResultDisplayDuration(0); // milliseconds to display result on screen after scan
        //integrator.setWide();  // Wide scanning rectangle, may work better for 1D barcodes
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.initiateScan();
    }


    /**
     * function handle scan result
     * @param requestCode scanned code
     * @param resultCode  result of scanned code
     * @param intent intent
     */
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {
            //we have a result
            codeContent = scanningResult.getContents();
            codeFormat = scanningResult.getFormatName();

            // display it on screen
            formatTxt.setText("FORMAT: " + codeFormat);
            contentTxt.setText("CONTENT: " + codeContent);

        }else{
            Toast toast = Toast.makeText(getApplicationContext(),"No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
