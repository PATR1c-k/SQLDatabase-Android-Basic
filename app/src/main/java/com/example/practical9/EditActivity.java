package com.example.practical9;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    RegistrationAdapter regadapter;
    int rowId;
    Cursor c;
    String fNameValue, lNameValue;
    EditText fname, lname;
    Button editSubmit, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editregister);
        fname = (EditText) findViewById(R.id.et_editfname);
        lname = (EditText) findViewById(R.id.et_editlname);
        editSubmit = (Button) findViewById(R.id.btn_update);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        Bundle showData = getIntent().getExtras();
        rowId = showData.getInt("keyid");
        regadapter = new RegistrationAdapter(this);
        c = regadapter.queryAll(rowId);
        if (c.moveToFirst())
        {
            do {
                fname.setText(c.getString(1));
                lname.setText(c.getString(2));
            } while (c.moveToNext());
        }

        editSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                regadapter.updateldetail(rowId, fname.getText().toString(), lname.getText().toString());
                finish();
            }
        });

        btnDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                regadapter.deletOneRecord(rowId);
                finish();
            }
        });
    }
}