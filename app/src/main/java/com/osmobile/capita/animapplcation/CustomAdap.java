package com.osmobile.capita.animapplcation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class CustomAdap extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_adap);

        listView =(ListView)findViewById(R.id.lstCustom);

        DesertAdapter adap = new DesertAdapter(this, new String[]{"This is a desert", "so is this", "this too",
        "and me", "me too", "and me 3", "and me 4"});
        listView.setAdapter(adap);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String res = parent.getItemAtPosition(position).toString();

                Toast.makeText(CustomAdap.this, res, Toast.LENGTH_LONG);
            }
        });
    }
}
