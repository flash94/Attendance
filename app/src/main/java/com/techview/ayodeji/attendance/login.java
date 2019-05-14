package com.techview.ayodeji.attendance;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import static com.techview.ayodeji.attendance.Constants.PRODUCTION;

public class login extends Activity {

    String EmailHolder, PasswordHolder;
    Boolean EditTextEmptyHolder;
    Cursor cursor;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    //SQLiteDatabase sqLiteDatabaseObj;
    String TempPassword = "NOT_FOUND" ;
    private EditText Name;
    private  EditText password;
    private TextView Info;
    private Button Login;
    private Button Reg;
    private Button test;
    public static final String User = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Opening SQLite database write permission.
        db = (new DatabaseHelper(this)).getWritableDatabase();
        Name = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.reginfo);
        Login = (Button)findViewById(R.id.btnLogin);
        Reg = (Button)findViewById(R.id.btnReg);


        if(!PRODUCTION){
            Name.setText("1234567890");
            password.setText("password");
        }

        /*test.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void  onClick (View v){
                openscanActivity();
            }
        });*/

        Reg.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void  onClick (View v){
                openregActivity();
            }
        });

        //Adding click listener to log in button.
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Calling EditText is empty or no method.
                CheckEditTextStatus();

                // Calling login method.
                LoginFunction();


            }
        });
    }

    // Login function starts from here.
    public void LoginFunction() {

        if (EditTextEmptyHolder) {
            // Adding search email query to cursor.
            cursor = db.query(DatabaseHelper.TABLE_NAME, null, " " + DatabaseHelper.COL3 + "=?", new String[]{EmailHolder}, null, null, null);

            Log.e("Login: ",DatabaseUtils.dumpCursorToString(cursor));
            while (cursor.moveToNext()) {

                if (cursor.isFirst()) {

                    cursor.moveToFirst();

                    // Storing Password associated with entered email.
                    TempPassword = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL5));

                    // Closing cursor.
                    cursor.close();
                }
            }
            // Calling method to check final result ..
            CheckFinalResult();

        }
        else {

            //If any of login EditText empty then this block will be executed.
            Toast.makeText(login.this,"Please Enter UserName or Password.",Toast.LENGTH_LONG).show();

        }
    }
    // Checking EditText is empty or not.
    public void CheckEditTextStatus(){

        // Getting value from All EditText and storing into String Variables.
        EmailHolder = Name.getText().toString();
        PasswordHolder = password.getText().toString();

        // Checking EditText is empty or no using TextUtils.
        if( TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)){

            EditTextEmptyHolder = false ;

        }
        else {

            EditTextEmptyHolder = true ;
        }
    }
    public void  openregActivity(){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
    public void  openscanActivity(){
        Intent intent = new Intent(this, Scan.class);
        startActivity(intent);
    }
    // Checking entered password from SQLite database email associated password.
    public void CheckFinalResult(){

        if(TempPassword.equalsIgnoreCase(PasswordHolder))
        {
            Toast.makeText(login.this,"Login Successfully",Toast.LENGTH_LONG).show();

            // Going to Dashboard activity after login success message.
            Intent intent = new Intent(login.this, Home.class);

            // Sending Email to Dashboard Activity using intent.
            intent.putExtra(User, EmailHolder);

            startActivity(intent);
        }
        else {
            Toast.makeText(login.this,"UserName or Password is Wrong, Please Try Again.",Toast.LENGTH_LONG).show();
        }

        TempPassword = "NOT_FOUND" ;
    }

}
