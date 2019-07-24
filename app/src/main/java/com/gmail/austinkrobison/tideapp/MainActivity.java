package com.gmail.austinkrobison.tideapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {
    private RSSFeed tideItems;
    static final String TOPROW = "topRow";
    static final String BOTTOMROW = "botRow";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FileIO io = new FileIO(getApplicationContext());
        tideItems = io.readFile();
        //adapter  array
        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
        //loops through items and adds them to the a hashmap to be stored in "data" array for adapter
        for( RSSItem item: tideItems.getAllItems()){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put(TOPROW, item.getNeatTopRow());
            map.put(BOTTOMROW, item.getNeatBottomRow());
            data.add(map);
        }
        //adapter takes the data we need from each item in "data" and stores them in appropriate textviews
        SimpleAdapter adapter = new SimpleAdapter(this,
                data,
                R.layout.listview_item,
                new String[]{TOPROW, BOTTOMROW},
                new int[]{R.id.topField, R.id.bottomField});

        ListView itemsListView =  findViewById(R.id.itemsListView);
        itemsListView.setAdapter(adapter);
        itemsListView.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        //handles the click on listview items
        RSSItem item = tideItems.getItem(position);
        Toast.makeText(this,
                item.getPredInFeet(), Toast.LENGTH_LONG).show();
    }
}
