package com.example.todolist;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import androidx.navigation.Navigation;

public class MainActivity extends AppCompatActivity implements INavigationListener {
    private Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new Database(getApplicationContext());
    }

    @Override
    public void showCreateListFragment() {
       // Navigation.findNavController(view).navigate(R.id.to_CreateListFragment);

        FragmentManager manager=getSupportFragmentManager();
        CreateListFragment createListFragment=new CreateListFragment();
        createListFragment.show(manager,"DIALOG");
    }



    public Database getDatabase() {
        return db;
    }
}
