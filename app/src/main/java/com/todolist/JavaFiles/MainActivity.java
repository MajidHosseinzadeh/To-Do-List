package com.todolist.JavaFiles;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toolbar;

import com.example.todo.CustomAdapter;
import com.example.todo.ItemsViewModel;
import com.google.android.material.navigation.NavigationView;
import com.todolist.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;

    RecyclerView recyclerCategories, recyclerTasks;
    ConstraintLayout layout;
    CalendarView calenderView;
    ArrayList<String> dayys = new ArrayList<>();
    ArrayList<ItemsViewModel> data = new ArrayList<ItemsViewModel>();
    boolean calFlag = true;

    public void calenderClick(View view){
        if (calFlag) {
            calenderView.setScaleX(1f);
            calenderView.setScaleY(1f);
            calFlag = false;

        }else {
            calenderView.setScaleX(0f);
            calenderView.setScaleY(0f);
            calFlag = true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calenderView = findViewById(R.id.calendarView);
        calenderView.setScaleX(0f);
        calenderView.setScaleY(0f);

        recyclerTasks = findViewById(R.id.recyclerview);
        recyclerTasks.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerTasks.setLayoutManager(verticalLayoutManager);
        List<ItemsViewModel> list = new ArrayList<ItemsViewModel>();
        list.add(new ItemsViewModel("sasas"));
        CustomAdapter customAdapter = new CustomAdapter(list);
        recyclerTasks.setAdapter(customAdapter);

        recyclerCategories = findViewById(R.id.recyclerView);
        layout = findViewById(R.id.layout);
        dayys.add("Test");
        recyclerCategories.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerCategories.setLayoutManager(horizontalLayoutManager);
        CategoryAdapter myAdapter = new CategoryAdapter(this, dayys);
        recyclerCategories.setAdapter(myAdapter);
        recyclerCategories.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerCategories, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        BlankFragment blankFragment = new BlankFragment(dayys.get(position));

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );


        // toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(findViewById(R.id.toolbar));

        // This will display an Up icon (<-), we will replace it with hamburger later
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        // Setup drawer view
        setupDrawerContent(nvDrawer);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
            /*Class fragmentClass;
            switch(menuItem.getItemId()) {
                case R.id.nav_first_fragment:
                    fragmentClass = FirstFragment.class;
                    break;
                case R.id.nav_second_fragment:
                    fragmentClass = SecondFragment.class;
                    break;
                case R.id.nav_third_fragment:
                    fragmentClass = ThirdFragment.class;
                    break;
                default:
                    fragmentClass = FirstFragment.class;
            }

             */
/*
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

 */
        // ...
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
