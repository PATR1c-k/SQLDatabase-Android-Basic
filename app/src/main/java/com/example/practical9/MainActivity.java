package com.example.practical9;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    RegistrationAdapter adapter_ob;
    RegistrationOpenHelper helper_ob;
    SQLiteDatabase db_ob;
    ListView nameList;
    Button registerBtn;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameList = (ListView) findViewById(R.id.lv_name);
        registerBtn = (Button) findViewById(R.id.btn_register);
        adapter_ob = new RegistrationAdapter(this);
        String[] from = {helper_ob.FNAME, helper_ob.LNAME};
        int[] to = {R.id.tv_fname, R.id.tv_lname};
        cursor = adapter_ob.queryName();
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, R.layout.row, cursor, from, to);
        nameList.setAdapter(cursorAdapter);

        nameList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                Bundle passdata = new Bundle();
                Cursor listCursor = (Cursor) arg0.getItemAtPosition(arg2);
                @SuppressLint("Range") int nameId = listCursor.getInt(listCursor.getColumnIndex(helper_ob.KEY_ID));
                // Toast.makeText(getApplicationContext(),
                // Integer.toString(nameId), 500).show();
                passdata.putInt("keyid", nameId);
                Intent passIntent = new Intent(MainActivity.this, EditActivity.class);
                passIntent.putExtras(passdata);
                startActivity(passIntent);
            }
        });

        registerBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent registerIntent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(registerIntent);
            }
        });
    }
    @Override
    public void onResume()
    {
        super.onResume();
        cursor.requery();
    }
}