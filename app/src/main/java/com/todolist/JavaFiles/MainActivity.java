package com.todolist.JavaFiles;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

import com.example.todo.CustomAdapter;
import com.example.todo.ItemsViewModel;
import com.todolist.R;

import java.util.ArrayList;

public class MainActivity extends Activity{

    RecyclerView recyclerView , recyclerview;
    ConstraintLayout layout;
    ArrayList<String> dayys = new ArrayList<>();
    ArrayList<ItemsViewModel> data = new ArrayList<ItemsViewModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(verticalLayoutManager);
        CustomAdapter customAdapter = new CustomAdapter(data);
        recyclerview.setAdapter(customAdapter);

        recyclerView = findViewById(R.id.recyclerView);
        layout= findViewById(R.id.layout);
        dayys.add("majidam");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        CategoryAdapter myAdapter = new CategoryAdapter(this, dayys);
        recyclerView.setAdapter(myAdapter);

    }
}