package com.example.bankingappusingsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.renderscript.AllocationAdapter;

import java.util.ArrayList;
import java.util.List;

public class AllUser extends AppCompatActivity {

    RecyclerView recyclerView;
    List<model> dataholder=new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    AllUserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_user);

        recyclerView=findViewById(R.id.rv_alluser);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        getSupportActionBar().setTitle("All User");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rowShow();
    }

    private void rowShow(){
        dataholder.clear();

        Cursor cursor=new  Database(this).readalldata();

        while (cursor.moveToNext()){
            model md =new model(cursor.getString(0),cursor.getString(1),cursor.getString(3),cursor.getString(6));
            this.dataholder.add(md);
        }

        adapter = new AllUserAdapter(AllUser.this,dataholder);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}