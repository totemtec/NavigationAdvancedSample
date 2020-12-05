package com.totemtec.navigation;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.ui.NavigationUI;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    LiveData<NavController> currentNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            setupBottomNavigationBar();
        } else {
//            Else, need to wait for onRestoreInstanceState
        }
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar();
    }

    private void setupBottomNavigationBar() {
        CustomBottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);

        // Setup the bottom navigation view with a list of navigation graphs
        List<Integer> navGraphIds = Arrays.asList(R.navigation.home, R.navigation.dashboard, R.navigation.notifications);

        LiveData<NavController> controller = bottomNavigationView.setupWithNavController(
                navGraphIds,
                getSupportFragmentManager(),
                R.id.nav_host_container,
                getIntent());

        // Whenever the selected controller changes, setup the action bar.
        controller.observe(this, new Observer<NavController>() {
            @Override
            public void onChanged(NavController navController) {
                NavigationUI.setupActionBarWithNavController(MainActivity.this, navController);
            }
        });

        currentNavController = controller;
    }


    public boolean onSupportNavigateUp() {
        if (currentNavController != null && currentNavController.getValue() != null && currentNavController.getValue().navigateUp())
        {
            return true;
        }

        return false;
    }

}