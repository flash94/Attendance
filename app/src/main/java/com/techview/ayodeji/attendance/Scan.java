package com.techview.ayodeji.attendance;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
//import android.support.v4.app.ActivityCompat;
import android.support.constraint.solver.widgets.WidgetContainer;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
public class Scan extends AppCompatActivity {

    SurfaceView surfaceView;
    CameraSource cameraSource;
    TextView textView;
    EditText course;
    BarcodeDetector barcodeDetector;
    Button logout, save;
    SQLiteDatabase db;
    String matricNo;

    static int MY_PERMISSIONS_REQUEST_CAMERA = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        surfaceView = (SurfaceView)findViewById(R.id.cameraPreview);
        textView = (TextView)findViewById(R.id.textvalue);
        logout = (Button)findViewById(R.id.logoutbtn);
        //course = (EditText) findViewById(R.id.Course);
        save = (Button)findViewById(R.id.SaveValue);


        db= (new DatabaseHelper(this)).getWritableDatabase();

        ((Button)findViewById(R.id.viewMatric)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Scan.this, Views.class));
            }
        });
        getCamera();
        // Adding click listener to Log Out button.
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String cos = course.getText().toString();
//                matricNo = "124787";
                if(matricNo == null || matricNo == ""){
                    Toast.makeText(Scan.this,"Empty Matric Number", Toast.LENGTH_LONG).show();
                }else{
                    saveMatric(matricNo);
                    //saveMatric(matricNo, cos);

                }
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Finishing current DashBoard activity on button click.
                finish();

                Toast.makeText(Scan.this,"Log Out Successful", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Scan.this, login.class);
                startActivity(intent);
            }
        });
    }

    private void saveMatric(String matricNo) {
        ContentValues cv = new ContentValues();
        cv.put("matric_number", matricNo);
        //cv.put("matric_number", cos);
        if(db.insert("matric_number", null, cv) == -1){
            Toast.makeText(this,"Unable to save matric number", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Matric Number Saved Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    private void getCamera() {
        barcodeDetector= new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();

        cameraSource = new CameraSource.Builder(this,barcodeDetector).setRequestedPreviewSize(640,480).build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback(){
            @Override
            public void surfaceCreated(SurfaceHolder holder){
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
// Permission is not granted
                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(Scan.this,
                            Manifest.permission.CAMERA)) {
                        // Show an explanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.
                    } else {
                        // No explanation needed; request the permission
                        ActivityCompat.requestPermissions(Scan.this,
                                new String[]{Manifest.permission.CAMERA},
                                MY_PERMISSIONS_REQUEST_CAMERA);

                        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                        // app-defined int constant. The callback method gets the
                        // result of the request.
                    }
                }else{
                    try{
                        cameraSource.start(holder);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void  surfaceChanged(SurfaceHolder holder, int format, int width, int height){

            }
            @Override
            public void surfaceDestroyed (SurfaceHolder holder){
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrCodes = detections.getDetectedItems();
                if (qrCodes.size() !=0){
                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                            Vibrator vibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(1000);
                            matricNo = qrCodes.valueAt(0).displayValue;
                            textView.setText(matricNo);
                        }
                    });
                }
            }
        });


    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 101: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getCamera();

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }
}

