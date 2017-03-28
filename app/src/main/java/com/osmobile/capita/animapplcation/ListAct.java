package com.osmobile.capita.animapplcation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListAct extends AppCompatActivity {

    ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lst = (ListView)findViewById(R.id.lstView);
        String[] stuff = {"First", "Second", "Third"};
        ListAdapter adap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stuff);
        lst.setAdapter(adap);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener()
                                   {

                                       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                           String itemVal = String.valueOf(parent.getItemAtPosition(position));
                                           Toast.makeText(ListAct.this, itemVal, Toast.LENGTH_LONG).show();

                                       }
                                   }
        );


    }
}
