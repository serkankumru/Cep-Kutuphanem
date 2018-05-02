package com.example.taner.cep_kutuphanem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class List1Activity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list1);



        listView = (ListView) findViewById(R.id.list1);

        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(List1Activity.this, android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.LokalKitaplar));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(List1Activity.this, KitapLocalActivity.class);
                intent.putExtra("bk1", listView.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });
        listView.setAdapter(mAdapter);
    }
}
