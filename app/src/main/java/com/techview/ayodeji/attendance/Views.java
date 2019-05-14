package com.techview.ayodeji.attendance;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Views extends AppCompatActivity {

    //private ListAdapter listAdapter;
    private ListAdapter listAdapter;
    SQLiteDatabase db;
    ArrayList<MatricNumber> matricNumbers = new ArrayList<>();
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("View Matric Numbers");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db= (new DatabaseHelper(this)).getWritableDatabase();
        Cursor res = db.rawQuery("select * from matric_number",null);
        Log.e("ViewActivity", DatabaseUtils.dumpCursorToString(res));
        while(res.moveToNext()){
            matricNumbers.add(new MatricNumber(
                    res.getLong(res.getColumnIndex("ID")),
                    res.getString(res.getColumnIndex("matric_number"))
            ));
        }

        Log.e("Matric: ", matricNumbers.toString());
        if(matricNumbers.size() > 0){
            listAdapter = new ListAdapter(this, matricNumbers);
            lv = (ListView) findViewById(R.id.lv);
            lv.setAdapter(listAdapter);
        }
    }
}
