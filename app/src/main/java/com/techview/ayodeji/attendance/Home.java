package com.techview.ayodeji.attendance;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    private Button logout;
            private Button Scan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        logout = (Button)findViewById(R.id.printbtn);
        Scan = (Button)findViewById(R.id.scanbtn);

        Scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openscanActivity();
            }
        });
        //Print.setOnClickListener(new View.OnClickListener(){
          //  @Override
          //  public void onClick(View view) {
              //  open
           // }
        //});
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Finishing current DashBoard activity on button click.
                finish();

                Toast.makeText(Home.this,"Log Out Successful", Toast.LENGTH_LONG).show();

            }
        });
    }
    public void  openscanActivity(){
        Intent intent = new Intent(this, Scan.class);
        startActivity(intent);
    }
}

