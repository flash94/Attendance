package com.techview.ayodeji.attendance;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.techview.ayodeji.attendance.Constants.PRODUCTION;


public class Register extends AppCompatActivity {

    SQLiteDatabase db;
    EditText fullname, staffID,Department, Spassword;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db= (new DatabaseHelper(this)).getWritableDatabase();

        fullname = (EditText)findViewById(R.id.fname);
        staffID = (EditText)findViewById(R.id.staffId);
        Department = (EditText)findViewById(R.id.Sdepartment);
        Spassword = (EditText)findViewById(R.id.SPassword);

        if(!PRODUCTION){
            fullname.setText("Full Name");
            staffID.setText("1234567890");
            Department.setText("CSE");
            Spassword.setText("password");
    }
        register = (Button)findViewById(R.id.reg);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fname = fullname.getText().toString();
                String staff = staffID.getText().toString();
                String dp = Department.getText().toString();
                String pass = Spassword.getText().toString();
                //Validation
                if(TextUtils.isEmpty(fname)){
                    fullname.setError("Fullname is Empty"); return;
                }

                if(TextUtils.isEmpty(staff)){
                    staffID.setError("Staff Id is Empty"); return;
                }

                if(TextUtils.isEmpty(dp)){
                    Department.setError("Department is Empty"); return;
                }

                if(TextUtils.isEmpty(pass)){
                    Spassword.setError("Password is Empty"); return;
                }


                long response = insertdata(fname,staff,dp,pass);
                if(response != -1){
                    Toast.makeText(getApplicationContext(), "register successfully",Toast.LENGTH_LONG).show();
                    //Empty edit text after data has been inserted
                    EmptyEditTextAfterDataInsert();
                }
                else
                    Toast.makeText(getApplicationContext(), "An Error Occurred...",Toast.LENGTH_LONG).show();
            }
        });
        //openHelper = new DataBaseHelper(this);
        //mDatabaseHelper = new DatabaseHelper(this);
    }
    public long  insertdata(String fname,String staff,String dp,String pass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL2, fname);
        contentValues.put(DatabaseHelper.COL3, staff);
        contentValues.put(DatabaseHelper.COL4, dp);
        contentValues.put(DatabaseHelper.COL5, pass);
        return db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }
    // Empty edittext after done inserting process method.
    public void EmptyEditTextAfterDataInsert(){

        fullname.getText().clear();

        staffID.getText().clear();

        Department.getText().clear();

        Spassword.getText().clear();

    }
}
