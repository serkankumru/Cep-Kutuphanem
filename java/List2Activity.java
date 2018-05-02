package com.example.taner.cep_kutuphanem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class List2Activity extends AppCompatActivity {
    ListView list2View;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list2);

        list2View = (ListView) findViewById(R.id.list2);
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(List2Activity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Internetkitaplar));

        list2View.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(List2Activity.this, InternetActivity.class);
                intent.putExtra("ktp2", list2View.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });
        list2View.setAdapter(mAdapter);
    }
}
