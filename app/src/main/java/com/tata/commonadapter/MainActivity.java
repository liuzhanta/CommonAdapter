package com.tata.commonadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyAdapter myAdapter;
    private ListView mlistview;
    private List<SimpleModel> mData = new ArrayList<>(30);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mlistview = (ListView) findViewById(R.id.listview);

        for (int i = 0; i < 30; i++) {
            mData.add(new SimpleModel("title---" + i, R.drawable.duckling));
        }

        myAdapter = new MyAdapter(this);
        mlistview.setAdapter(myAdapter);
        myAdapter.setData(mData);
    }
}
