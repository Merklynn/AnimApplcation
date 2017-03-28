package com.osmobile.capita.animapplcation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DataBase extends AppCompatActivity {

    Button addBut;
    Button delBut;
    EditText txtEntry;
    ListView lstView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);

        addBut = (Button)findViewById(R.id.btnDataAdd);
        delBut = (Button)findViewById(R.id.btnDataDel);
        txtEntry = (EditText)findViewById(R.id.editDBName);
        lstView = (ListView) findViewById(R.id.lstDatabase);

        PopulateList();

        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                txtEntry.setText(text);
            }
        });
        addBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddItem();
            }
        });
        delBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteItem();
            }
        });
    }

    private  void  AddItem()
    {

        String text = txtEntry.getText().toString();
        if(text != null && text.length() > 0)
        {
            AnimDBManager db = new AnimDBManager(this, null);
            db.addProduct(new Product(text));
            PopulateList();
            txtEntry.setText("");
        }

    }

    private  void  DeleteItem()
    {

        String text = txtEntry.getText().toString();
        if(text != null && text.length() > 0)
        {
            AnimDBManager db = new AnimDBManager(this, null);
            db.deleteProduct(text);
            PopulateList();
            txtEntry.setText("");
        }

    }

    private void PopulateList() {
        ArrayList<String> items = getGetDatabaseItems();
        String[] stuff = items.toArray(new String[0]);
        ListAdapter strAd = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stuff);
        lstView.setAdapter(strAd);
    }

    private ArrayList<String> getGetDatabaseItems() {
       AnimDBManager db = new AnimDBManager(this, null);
        ArrayList<String> strings = db.SelectFromProducts();
        return  strings;
    }
}
