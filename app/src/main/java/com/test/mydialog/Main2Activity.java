package com.test.mydialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    RecyclerView rv_list;
    ListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ///
        setContentView(R.layout.activity_main2);
        rv_list= (RecyclerView) findViewById(R.id.rv_list);
        initView();
    }
    private void initView() {
        adapter=new ListAdapter(this,R.layout.listitem);
        List<String> list=new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("list_item");
        }

        adapter.setList(list);

        rv_list.setLayoutManager(new LinearLayoutManager(this));
//        rv_list.setNestedScrollingEnabled(false);
//        rv_list.addItemDecoration(getItemDivider());
        rv_list.setAdapter(adapter);

    }
}
